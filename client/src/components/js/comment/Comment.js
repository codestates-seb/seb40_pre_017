import axios from 'axios';
import React from 'react'
import '../../css/comment/Comment.scss'
import createdAt from '../createdAt/CreatedAt';
import { useParams, useNavigate } from 'react-router-dom';

export default function Comment({id, content, setEditClick, type, setEditValue, setCommentId, accessToken, answerId}) {
  let params  = useParams();

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
      if (window.confirm("Are you sure you want to delete the comment?") === true) {
        axios.delete(`/api/question/${id}/comments/${content.questionCommentId}`)
        .then((res) => {
          if(res.status === 200) {
            window.location.href = `/questions/${params.id}`;
          }
        })
        .catch(error => {
          alert(error.response.data.errors[0].reason);
        });
      }
    }else if(type === 'answer'){
      if (window.confirm("Are you sure you want to delete the comment?") === true) {
        axios.delete(`/api/question/${id}/answer/${answerId}/comments/${content.answerCommentId}`)
        .then((res) => {
          if(res.status === 200) {
            window.location.href = `/questions/${params.id}`;
          }
        })
        .catch(error => {
          console.log(error)
          alert(error.response.data.errors[0].reason);
        });
      }
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
