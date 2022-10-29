import React from 'react'
import Comment from './Comment'
import '../../css/comment/CommentList.scss'

export default function CommentList({item}) {
  return (
    <div className='commentList'>
      {item && item.map(question => (
        <div className='commentLine'>
        <Comment question={question}/>
        </div>
      ))}
        
        <input className='commentInput' type='text' placeholder='Add a comment'></input>
    </div>
  )
}
