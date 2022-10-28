import React from 'react'
import QuestionList from '../components/js/questionPage/QuestionList'
import { Link } from 'react-router-dom'
import './QuestionPage.scss'
import Pagination from '../components/js/questionPage/Pagination';

export default function QuestionPage({items}) {
  //questionList Count
  let count = 0;
  if(items){
    count = items.length;
  }

  return (
    <div className='questionPageWrap'>
      <div className='questionPageNavbar'>Navbar</div>
      <div className='questionPage'>
        <div className='headAddWrap'>
          <h1>All Questions</h1>
          <Link to={'/add'}>
            <button>Ask Question</button>
          </Link>
        </div>
        <div className='countFilterWrap'>
          <span>{count} question</span>
          <div className='filterBtns'>
            <button>Newest</button>
            <button>Vote</button>
            <button>Unanswered</button>
          </div>
        </div>
        <QuestionList items={items}/>
        <Pagination/>
      </div>
      <div className='questionPageAside'>aside</div>
    </div>
    
  )
}
