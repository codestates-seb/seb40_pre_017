import React from 'react'
import '../../css/questionPage/Question.scss'
import { Link } from 'react-router-dom'
import Tags from '../tags/Tags'

export default function Question({item}) {
  //content 글자수 제한
  let contentTextLimit = item.question.content.slice(0,197)
  if(contentTextLimit.length >= 196){
    contentTextLimit = contentTextLimit + '...'
  }

  return (
    <div className='questionBox'>
      <div className='question_sideBox'>
        <p>{item.question.votes} vote</p>
        <p>{item.question.answer_count} answers</p>
        <p>{item.question.view_count} views</p>
      </div>
      <div className='question_mainBox'>
        <Link to={`/questions/${item.question.questionId}`}>
          <h2>{item.question.title}</h2>
        </Link>
        <p>{contentTextLimit}</p>
        <div className='question_bottomBox'>
          <Tags item={item}/>
        </div>
      </div>
      <div className='questionList_profile'>
            <img className='pic' src={item.member.profileImage} alt='profile'/>
            <p>{item.member.userName}</p>
            <p>createdtime ago</p>
        </div>
    </div>
  )
}
