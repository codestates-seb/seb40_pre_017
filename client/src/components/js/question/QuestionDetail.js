import React from 'react'
import CommentList from '../comment/CommentList'
import Profile from '../profile/Profile'
import Vote from '../vote/Vote'
import Tags from '../tags/Tags'
import '../../css/question/QuestionDetail.scss'

export default function QuestionDetail({item}) {

  return (
    <div className='questionDetail'>
      <Vote item={item.question.votes}/>
      <div className='detailMainWrap'>
        <div className='detailContent'>
          <h3>{item.question.content}</h3>
          <Tags item={item}/>
        </div>

        <div className='detailBottomWrap'>
          <div className='detailEditWrap'>
            <button>Share</button>
            <button>Edit</button>
          </div>
          <Profile item={item.member}/>
        </div>
        <CommentList item={item.question.qcomment}/>
      </div>      
    </div>
  )
}
