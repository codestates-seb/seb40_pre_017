import React from 'react'
import CommentList from '../comment/CommentList'
import Profile from '../profile/Profile'
import Vote from '../vote/Vote'
import Tags from '../tags/Tags'
import '../../css/question/QuestionDetail.scss'
import { Link } from 'react-router-dom'
import { fetchDelete } from '../../../util/api'

export default function QuestionDetail({item, id}) {

  const handleDelete = () => {
    // 임시 DELETE
    fetchDelete('http://localhost:3001/items/', item.id)

    // api DELETE
    // fetchDelete('http://localhost:3001/questions/', id)
  }

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
            <Link to={`/questions/${id}/edit`}>
              <button>Edit</button>
            </Link>
            <button onClick={handleDelete}>Delete</button>
          </div>
          <Profile item={item.member}/>
        </div>
        <CommentList item={item.question.qcomment} type={'question'} temporary={item}/>
      </div>      
    </div>
  )
}
