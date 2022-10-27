import React from 'react'
import CommentList from '../comment/CommentList'
import Profile from '../profile/Profile'
import Vote from '../vote/Vote'
import { Link } from 'react-router-dom'
import Tags from '../tags/Tags'
import '../../css/question/QuestionDetail.scss'

export default function QuestionDetail({item}) {

  const test = () => {
    console.log(item.question.title) 
  }

  return (
    <div className='questionDetail'>
      <div className='detailHeadWrap'>
        <h1>{item.question.title}</h1>
        <Link to={'/add'}>
          <button>Ask Question</button>
        </Link>
      </div>

      <div className='detailDateWrap'>
        <p>Asked</p>
        <p className='detailDateValue'>{item.question.createdAt}</p>
        <p>Modefied</p>
        <p className='detailDateValue'>{item.question.modifiedAt}</p>
        <p>Viewed</p>
        <p className='detailDateValue'>{item.question.viewCount} times</p>
      </div>
      
      <div className='detailMainWrap'>
        <Vote item={item}/>
        <div className='detailContent'>
          <h3>{item.question.content}</h3>
          <Tags item={item}/>
        </div>

        <div className='detailBottomWrap'>
          <div className='detailEditWrap'>
            <button>Share</button>
            <button>Edit</button>
            <Profile item={item}/>
          </div>
        </div>
      </div>

      <button onClick={test}>Ask Question</button>
      
      <CommentList item={item}/>
    </div>
  )
}
