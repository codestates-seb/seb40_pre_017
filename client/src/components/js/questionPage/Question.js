import React, { useEffect, useState } from 'react'
import '../../css/questionPage/Question.scss'
import { useNavigate } from 'react-router-dom'
import Tags from '../tags/Tags'
import axios from 'axios'
import createdAt from '../createdAt/CreatedAt'

export default function Question({item}) {

  // 상세페이지 데이터 GET
  const [detailItem, setDetailItem] = useState();
  useEffect(()=>{
    axios.get(`/api/questions/${item.question.questionId}`, {
      headers: {
        "ngrok-skip-browser-warning": "69420"
      }
    })
    .then(res => {
      setDetailItem(res.data)
    })
    .catch(err => {
      console.error(err)
    })
  }, [item])

  const navigate = useNavigate();
  const clickTitle = () => {
    navigate(`/questions/${item.question.questionId}`, {
      state: {
        item : detailItem
      }
    })
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
