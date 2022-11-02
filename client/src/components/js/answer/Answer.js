import React, { useState } from 'react'
import Vote from '../vote/Vote'
import ProfileAnswer from '../profile/ProfileAnswer'
import CommentList from '../comment/CommentList'
import '../../css/answer/Answer.scss'
import { Link } from 'react-router-dom'
// import { fetchCreate, fetchDelete } from '../../../util/api'

export default function Answer({answer, id, answerId, item}) {

  // 질문 삭제
  const handleDelete = () => {
    // 임시 DELETE는 구현 하기 너무 까다로워서 일단 스킵
    
    // api DELETE
    // fetchDelete(`/question/${id}/answer/${answerId}/`)
  }

  //질문 채택
  const [ check, setCheck ] = useState(false);
  const handleCheck = () => {
    if(!check){
      // fetchCreate(`/question/${id}/answer/${answerId}/accept`)
      setCheck(true);
    }else{
      // fetchCreate(`/question/${id}/answer/${answerId}/accept/undo`)
      setCheck(false);
    }
  }

  return (
    <div className='answerWrap'>
      <div className='answerVote'>
        <Vote item={answer.votes}/>
        <button 
          onClick={handleCheck} 
          className={check ? 'select answerCheck' : 'select'}
        ><i className="fa-solid fa-check"></i></button>
      </div>
      
      <div className='answerMainWrap'>
        <h3>{answer.content}</h3>
        <div className='detailBottomWrap'>
          <div className='detailEditWrap'>
            <button>Share</button>
            <Link to={`/questions/${id}/editanswer/${answerId}`}>
              <button>Edit</button>
            </Link>
            <button onClick={handleDelete}>Delete</button>
          </div>
          <ProfileAnswer item={answer}/>
        </div>
        <CommentList item={answer.aComments} type={'answer'} temporary={item}/>
      </div>
    </div>
  )
}
