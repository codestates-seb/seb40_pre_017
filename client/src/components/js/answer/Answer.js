import React from 'react'
import Vote from '../vote/Vote'
import ProfileAnswer from '../profile/ProfileAnswer'
import CommentList from '../comment/CommentList'
import '../../css/answer/Answer.scss'
import { Link } from 'react-router-dom'

export default function Answer({answer, id, answerId}) {

  return (
    <div className='answerWrap'>
      <Vote item={answer.votes}/>
      <div className='answerMainWrap'>
        <h3>{answer.content}</h3>
        <div className='detailBottomWrap'>
          <div className='detailEditWrap'>
            <button>Share</button>
            <Link to={`/questions/${id}/editanswer/${answerId}`}>
              <button>Edit</button>
            </Link>
          </div>
          <ProfileAnswer item={answer}/>
        </div>
        <CommentList item={answer.aComments}/>
      </div>
    </div>
  )
}
