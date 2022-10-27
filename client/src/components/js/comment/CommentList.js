import React from 'react'
import Comment from './Comment'
import '../../css/comment/CommentList.scss'

export default function CommentList({item}) {
  return (
    <div className='commentList'>
      {item.question.qcomment && item.question.qcomment.map(question => (
        <div>
        <Comment question={question}/>
        </div>
      ))}
        
        <input type='text' placeholder='Add a comment'></input>
    </div>
  )
}
