import AnswerList from '../components/js/answer/AnswerList'
import QuestionDetail from '../components/js/question/QuestionDetail'
import { useLocation } from 'react-router-dom'
import Aside from '../components/js/aside/Aside';
import Category from '../components/js/category/Category';

import './DetailPage.scss'
import { Link } from 'react-router-dom'

export default function DetailPage({items}) {

  const location = useLocation();
  const { item } = location.state;

  return (
    <div className='detailPageWrap'>
      <div className='detailPageNavbar'>
        <Category />
      </div>
      <div className='detailPage'>
        <div className='detailHeadWrap'>
          <div className='detailTitleWrap'>
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
        </div>
        <div className='detailBodyWrap'>
          <div className='detailContentWrap'>
            <QuestionDetail item={item} id={item.question.questionId}/>
            <AnswerList item={item} id={item.question.questionId}/>
          </div>
          <div className='detailPageAside'>
            <Aside />
          </div>
        </div>
      </div>
    </div>
  )
}
