import React from 'react'
import CommentList from '../comment/CommentList'
import Profile from '../profile/Profile'
import Vote from '../vote/Vote'
import { Link } from 'react-router-dom'

export default function QuestionDetail() {

  //id 파라미터 가져오기
  let query = window.location.href;
  let param = new URLSearchParams(query).keys();
  
  const test = () => {
    console.log(param);
  }

  return (
    <div>
      <h1>{}</h1>
      <Link to={'/add'}>
        <button>Ask Question</button>
      </Link>
      <Profile />
      <button onClick={test}>Ask Question</button>
      <Vote />
      <CommentList />
    </div>
  )
}
