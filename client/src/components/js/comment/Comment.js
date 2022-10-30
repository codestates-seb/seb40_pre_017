import React from 'react'
import { fetchDelete } from '../../../util/api';
import '../../css/comment/Comment.scss'

export default function Comment({content, setEditClick, type}) {

  // click edit
  const clickEdit = () => {
    setEditClick(true);
  }

  // click delete
  const handleDelete = () => {
    if(type === 'question'){
      // fetchDelete("/question/{id}/comments/{comment-id}")
    }else if(type === 'answer'){
      // fetchDelete("question/{id}/answer/{answer-id}/comments/{comment-id}")
    }
  }
  return (
    <div className='commentWrap'>
      <div className='commentContent'>{content.content} -</div>
      <div className='commentName'>{content.userName}</div>
      <div className='commentTime'>{content.createdAt} ago</div>
      <i className="fa-solid fa-pencil" onClick={clickEdit}></i>
      <i className="fa-solid fa-trash-can" onClick={handleDelete}></i>
    </div>
  )
}
