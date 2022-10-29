import React from 'react'
import Vote from '../vote/Vote'
import ProfileAnswer from '../profile/ProfileAnswer'
import CommentList from '../comment/CommentList'
import '../../css/answer/Answer.scss'

export default function Answer({answer}) {

  return (
    <div className='answerWrap'>
      <Vote item={answer.votes}/>
      <div className='answerMainWrap'>
        <h3>{answer.content}</h3>
        <div className='detailBottomWrap'>
          <div className='detailEditWrap'>
            <button>Share</button>
            <button>Edit</button>
          </div>
          <ProfileAnswer item={answer}/>
        </div>
        <CommentList item={answer.aComments}/>
      </div>
    </div>
  )
}
