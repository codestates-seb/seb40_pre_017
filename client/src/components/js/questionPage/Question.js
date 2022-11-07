import React from 'react'
import '../../css/questionPage/Question.scss'
import { useNavigate } from 'react-router-dom'
import Tags from '../tags/Tags'
import createdAt from '../createdAt/CreatedAt'

export default function Question({item}) {

  const navigate = useNavigate();
  const clickTitle = () => {
    navigate(`/questions/${item.question.questionId}`)
  }

  return (
    <div className='questionBox'>
      <div className='question_sideBox'>
        <p>{item.question.voteCount} vote</p>
        <p>{item.question.answerCount} answers</p>
        <p>{item.question.viewCount} views</p>
      </div>
      <div className='question_mainBox'>
          <h2 onClick={clickTitle}>{item.question.title}</h2>
        <p>{item.question.summary}</p>
        <div className='question_bottomBox'>
          <Tags item={item}/>
        </div>
      </div>
      <div className='questionList_profile'>
            <img className='pic' src={item.member.profileImage} alt='profile'/>
            <p>{item.member.username}</p>
            <p>{createdAt(item.question.createdAt)}</p>
        </div>
    </div>
  )
}
