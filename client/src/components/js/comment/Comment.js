import React from 'react'
import '../../css/comment/Comment.scss'

export default function Comment({question}) {
  return (
    <div className='commentWrap'>
      <div className='commentContent'>{question.content} -</div>
      <div className='commentName'>{question.userName}</div>
      <div className='commentTime'>{question.createdAt} ago</div>
      <i className="fa-solid fa-pencil"></i>
      <i className="fa-solid fa-trash-can"></i>
    </div>
  )
}
