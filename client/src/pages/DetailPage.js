import AnswerList from '../components/js/answer/AnswerList'
import QuestionDetail from '../components/js/question/QuestionDetail'
import { useParams, useNavigate } from 'react-router-dom'
import Aside from '../components/js/aside/Aside';
import Category from '../components/js/category/Category';

import './DetailPage.scss'
import createdAt from '../components/js/createdAt/CreatedAt';
import { useEffect, useState } from 'react';
import axios from 'axios';

const REACT_APP_API_URL = process.env.REACT_APP_API_URL;

export default function DetailPage({ accessToken }) {
  axios.defaults.headers.common["Authorization"] = window.sessionStorage.getItem("jwtToken");
  axios.defaults.withCredentials = true;

  const [item, setItem] = useState(null);
  
  const navigate = useNavigate();
  
  let params = useParams();

  useEffect(()=>{
    axios.get(`${REACT_APP_API_URL}questions/${params.id}`, {
      headers: {
        "ngrok-skip-browser-warning": "69420"
      }
    })
    .then(res => {
      setItem(res.data)
    })
  }, []);

  const createQuestion = () => {
    if(window.sessionStorage.getItem("jwtToken")) {
      navigate("/add")
    }else{
      alert('This service requires login')
      localStorage.setItem("lastPath", `/questions/${params.id}`);
      navigate("/login")
    }
  }

  return (
    <div className='detailPageWrap'>
      <div className='detailPageNavbar'>
        <Category />
      </div>
      <div className='detailPage'>
        <div className='detailHeadWrap'>
          <div className='detailTitleWrap'>
            {item !== null && <h1>{item.question.title}</h1>}
            <button onClick={createQuestion}>Ask Question</button>
          </div>
          {item !== null && 
          <div className='detailDateWrap'>
            <p>Asked</p>
            <p className='detailDateValue'>{createdAt(item.question.createdAt)}</p>
            <p>Modefied</p>
            <p className='detailDateValue'>{createdAt(item.question.modifiedAt)}</p>
            <p>Viewed</p>
            <p className='detailDateValue'>{item.question.viewCount} times</p>
          </div>
          }
        </div>
        <div className='detailBodyWrap'>
          <div className='detailContentWrap'>
          {item !== null && 
          <>
            <QuestionDetail item={item} id={item.question.questionId} accessToken={accessToken} />
            <AnswerList item={item} id={item.question.questionId} accessToken={accessToken}/>
          </>
          }
          </div>
          <div className='detailAside'>
            <Aside/>
          </div>
        </div>
        
    </div>
  </div>
  )
}
