import React from 'react'
import '../../css/comment/Comment.scss'

export default function Comment({content}) {
  return (
    <div className='commentWrap'>
      <div className='commentContent'>{content.content} -</div>
      <div className='commentName'>{content.userName}</div>
      <div className='commentTime'>{content.createdAt} ago</div>
      <i className="fa-solid fa-pencil"></i>
      <i className="fa-solid fa-trash-can"></i>
    </div>
  )
}
