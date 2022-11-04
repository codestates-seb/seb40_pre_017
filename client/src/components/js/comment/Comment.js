import axios from 'axios';
import React from 'react'
import '../../css/comment/Comment.scss'
import createdAt from '../createdAt/CreatedAt';

export default function Comment({id, content, setEditClick, type, setEditValue, setCommentId, accessToken}) {
  axios.defaults.headers.common["Authorization"] = accessToken;


  // click edit
  const clickEdit = () => {
    setEditClick(true);
    setEditValue(content.content)
    if(type === 'question'){
      setCommentId(content.questionCommentId)
    }else if(type === 'answer'){
      setCommentId(content.answerCommentId)
    }
    
  }

  // click delete
  const handleDelete = () => {
    if(type === 'question'){
      // fetchDelete("/question/{id}/comments/{comment-id}")
      axios.delete(`/api/question/${id}/comments/${content.questionCommentId}`)
      .then((res) => {
        console.log(res)
      })
      .catch(error => {
        console.log(error.response);
      });
    }else if(type === 'answer'){
      // fetchDelete("question/{id}/answer/{answer-id}/comments/{comment-id}")
      axios.delete(`/api/question/${id}/answer/${content.answerCommentId}comments/${content.answerCommentId}`)
      .then((res) => {
        console.log(res)
        // window.location.replace(`/questions/${id}`)
      })
      .catch(error => {
        console.log(error.response);
      });
    }
  }
  return (
    <div className='commentWrap'>
      <div className='commentContent'>{content.content} -</div>
      <div className='commentName'>{content.userName}</div>
      <div className='commentTime'>{createdAt(content.createdAt)} ago</div>
      <i className="fa-solid fa-pencil" onClick={clickEdit}></i>
      <i className="fa-solid fa-trash-can" onClick={handleDelete}></i>
    </div>
  )
}
