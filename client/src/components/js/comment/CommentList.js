import React, { useState } from 'react'
import Comment from './Comment'
import '../../css/comment/CommentList.scss'
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom';

export default function CommentList({item, id, answerId, type, accessToken}) {

  let params  = useParams();
  const navigate = useNavigate();


  axios.defaults.headers.common["Authorization"] = accessToken;
  const [clickAdd, setClickAdd] = useState(false);
  const [editValue, setEditValue] = useState('');
  const [commentId, setCommentId] = useState('');

  // comment 추가
  const handleAdd = () => {
    setClickAdd(true);
  }

  //comment 추가 취소
  const handleAddCancle = () => {
    setClickAdd(false);
  }

  //comment input box 
  const [content, setContent] = useState('');
  const handleInput = (e) => {
    setContent(e.target.value);
  }

  let data = { content }

  //comment submit
  const handleSubmit = () => {
    
    if(type === 'question'){    
      axios.post(`/api/question/${id}/comments`, data)
      .then((res) => {
        if(res.status === 201) {
          console.log(res)
          window.location.href = `/questions/${params.id}`;
        }
      })
      .catch(error => {
        alert(error.response.data.errors[0].reason);
      });
      // /question/{id}/answer/{answer-id}/comments
    }else if(type === 'answer'){
      axios.post(`/api/question/${id}/answer/${answerId}/comments`, data)
      .then((res) => {
        if(res.status === 201) {
          console.log(res)
          window.location.href = `/questions/${params.id}`;
        }
      })
      .catch(error => {
        alert(error.response.data.errors[0].reason);
      });
    }
    setClickAdd(false);
    // window.location.replace(`/questions/${id}`)
  }

  //comment Edit
  const [editClick, setEditClick] = useState(false);
  const handleEdit = () => {
    if(type === 'question'){
      axios.patch(`/api/question/${id}/comments/${commentId}`, data)
      .then((res) => {
        if(res.status === 200) {
          window.location.href = `/questions/${params.id}`;
        }
      })
      .catch(error => {
        alert(error.response.data.errors[0].reason);
      });
    }else if( type === 'answer'){
      // fetchPatch("question/{id}/answer/{answer-id}/comments/{comment-id}")
      axios.patch(`/api/question/${id}/answer/${answerId}/comments/${commentId}`, data)
      .then((res) => {
        if(res.status === 200) {
          window.location.href = `/questions/${params.id}`;
        }
      })
      .catch(error => {
        alert(error.response.data.errors[0].reason);
      });
    }
    setEditClick(false);
    // window.location.replace(`/questions/${id}`)
  }

  //edit 취소
  const handleEditCancle = () => {
    setEditClick(false);
  }

  return (
    <div className='commentList'>
      {item && item.map(content => (
        <div className='commentLine'>
        <Comment 
          id={id}
          content={content} 
          setEditClick={setEditClick} 
          type={type} 
          setEditValue={setEditValue} 
          setCommentId={setCommentId}
          accessToken={accessToken}
          answerId={answerId}
          commentId={commentId}
        />
        </div>
      ))}
        
      {
        clickAdd ? 
          <div className='commentWrap'>
            <input type='text' onChange={handleInput}></input>
            <div className='commentBtnWrap'>
              <button onClick={handleSubmit} className='AddComment'>Add Comment</button>
              <button onClick={handleAddCancle} className='cancel'>Cancel</button>
            </div>
          </div>
          :
          <button className='addCommentBtn' onClick={handleAdd}>Add a comment</button>
      }

      {
        editClick ?
        <div className='commentWrap'>
          <input type='text' onChange={handleInput} defaultValue={editValue}></input>
          <div className='commentBtnWrap'>
            <button onClick={handleEdit} className='AddComment'>Save Edit</button>
            <button onClick={handleEditCancle} className='cancel'>Cancel</button>
          </div>
        </div>
        :
        null
      }
    </div>
  )
}
