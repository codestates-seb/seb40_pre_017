import AnswerList from '../components/js/answer/AnswerList'
import QuestionDetail from '../components/js/question/QuestionDetail'
import { useLocation, useParams } from 'react-router-dom'
import Aside from '../components/js/aside/Aside';
import Category from '../components/js/category/Category';

import './DetailPage.scss'
import { Link } from 'react-router-dom'
import createdAt from '../components/js/createdAt/CreatedAt';
import { useEffect, useState } from 'react';
import axios from 'axios';

export default function DetailPage({accessToken}) {
  let params = useParams();

  const location = useLocation();
  // const { item } = location.state;

  const [item, setItem] = useState();
  
    console.log(item)
    
  useEffect( ()=>{
const getPosts = async () => {
    const posts = await axios.get(`/api/questions/${params.id}`, {
      headers: {
        "ngrok-skip-browser-warning": "69420"
        }
      });
      setItem(posts.data)
    };

    getPosts();
  }, []);

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
            <p className='detailDateValue'>{createdAt(item.question.createdAt)}</p>
            <p>Modefied</p>
            <p className='detailDateValue'>{createdAt(item.question.modifiedAt)}</p>
            <p>Viewed</p>
            <p className='detailDateValue'>{item.question.viewCount} times</p>
          </div>
        </div>
        <div className='detailBodyWrap'>
          <div className='detailContentWrap'>
            <QuestionDetail item={item} id={item.question.questionId} accessToken={accessToken}/>
            <AnswerList item={item} id={item.question.questionId} accessToken={accessToken}/>
          </div>
          <div className='detailPageAside'>
            <Aside />
          </div>
        </div>
      </div>
    </div>
  )
}
