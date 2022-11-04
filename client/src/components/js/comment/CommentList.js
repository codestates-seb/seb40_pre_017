import React, { useState } from 'react'
import Comment from './Comment'
import '../../css/comment/CommentList.scss'
import axios from 'axios';

export default function CommentList({item, id, answerId, type, accessToken}) {
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
        console.log(res)
      })
      .catch(error => {
        console.log(error.response);
      });
      // /question/{id}/answer/{answer-id}/comments
    }else if(type === 'answer'){
      axios.post(`/api/question/${id}/answer/${answerId}/comments`, data)
      .then((res) => {
        console.log(res)
      })
      .catch(error => {
        console.log(error.response);
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
        console.log(res)
      })
      .catch(error => {
        console.log(error.response);
      });
    }else if( type === 'answer'){
      // fetchPatch("question/{id}/answer/{answer-id}/comments/{comment-id}")
      axios.patch(`/api/question/${id}/answer/${answerId}/comments/${commentId}`, data)
      .then((res) => {
        console.log(res)
      })
      .catch(error => {
        console.log(error.response);
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
