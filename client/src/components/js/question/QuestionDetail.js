import React from 'react'
import CommentList from '../comment/CommentList'
import Profile from '../profile/Profile'
import Vote from '../vote/Vote'
import Tags from '../tags/Tags'
import '../../css/question/QuestionDetail.scss'
import { Link } from 'react-router-dom'
import { fetchDelete } from '../../../util/api'
import { Viewer } from '@toast-ui/react-editor';
import '@toast-ui/editor/dist/toastui-editor-viewer.css';



export default function QuestionDetail({item, id, accessToken}) {

  const handleDelete = () => {
    // 임시 DELETE
    fetchDelete('http://localhost:3001/items/', item.id)

    // api DELETE
    // fetchDelete(`/api/questions/${id}`)
    fetch(`/api/questions/${id}`, {
      method: "DELETE",
      headers: new Headers({
        "ngrok-skip-browser-warning": "69420",
        "Content-Type" : "application/json"
      })
    })
  }

  return (
    <div className='questionDetail'>
      <Vote item={item.question.voteCount} type={'question'} id={item.question.questionId}/>
      <div className='detailMainWrap'>
        <div className='detailContent'>
          <h3><Viewer initialValue={item.question.summary}/></h3>
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
