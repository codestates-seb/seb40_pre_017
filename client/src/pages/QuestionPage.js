import React from 'react'
import QuestionList from '../components/js/questionPage/QuestionList'
import { Link } from 'react-router-dom'

export default function Mainpage() {

  return (
    <div>
      <div className='headAddWrap'>
        <h1>All Question</h1>
        <button>
          <Link to={'/add'}>Ask Question</Link>
        </button>
      </div>
      <div className='countFilterWrap'>
        <span>count question</span>
        <div className='filterBtns'>
          <button>Newest</button>
          <button>Vote</button>
          <button>Unanswered</button>
        </div>
      </div>
      
      
      <QuestionList />
    </div>
  )
}
