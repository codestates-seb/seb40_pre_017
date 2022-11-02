import React, { useState } from 'react'
import Comment from './Comment'
import '../../css/comment/CommentList.scss'
import { fetchCreate, fetchPatch } from '../../../util/api';

export default function CommentList({item, type, temporary}) {
  const [clickAdd, setClickAdd] = useState(false);

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

  //comment submit
  const handleSubmit = () => {
    // data POST 임시
    let data = Object.assign(temporary.question)
    data.qcomment = [
      {
        "qCommnetId": 31187802,
        "memberId": 21187802,
        "userName": "baptiste",
        "link": "https://stackoverflow.com/users/11187800/fulvio",
        "content": content,
        "createdAt": 1552344257,
        "modifiedAt": 1596034268
      }
    ]
    
    if(type === 'question'){
      fetchPatch("http://localhost:3001/items/",temporary.id, { question: data })
    
      // fetchCreate("/question/{id}/comments", data)
    }else if(type === 'answer'){
      // fetchCreate("/question/{id}/answer/{answer-id}comments", data)
    }
    setClickAdd(false);
  }

  //comment Edit
  const [editClick, setEditClick] = useState(false);
  const handleEdit = () => {
    if(type === 'question'){
      // fetchPatch("/question/{id}/comments/{comment-id}")
    }else if( type === 'answer'){
      // fetchPatch("question/{id}/answer/{answer-id}/comments/{comment-id}")
    }
    setEditClick(false);
  }

  //edit 취소
  const handleEditCancle = () => {
    setEditClick(false);
  }


  return (
    <div className='commentList'>
      {item && item.map(content => (
        <div className='commentLine'>
        <Comment content={content} setEditClick={setEditClick} type={type}/>
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
          <input type='text' onChange={handleInput}></input>
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
