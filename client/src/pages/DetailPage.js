import React, { useEffect, useState } from 'react'
import AnswerList from '../components/js/answer/AnswerList'
import QuestionDetail from '../components/js/question/QuestionDetail'
import { useParams } from 'react-router-dom'
import Aside from '../components/js/aside/Aside';
import Category from '../components/js/category/Category';

import './DetailPage.scss'
import { Link } from 'react-router-dom'
import axios from 'axios';

export default function DetailPage({items}) {
  //오류! 새로고침시 useParams가 안불러짐
  //AnswerList sorted by 만들어야함
  //AddContent 마크다운 추가

  //id 파라미터 가져오기
  let paramsId  = useParams();
  
  //params로 questionData 가져오기
  // let item = items.filter(item => (
  //   item.question.questionId === Number(params.id)
  // ))[0]
  const [item, setItem] = useState();
  useEffect(()=>{
    // let params = {
    //   "questionId" : paramsId.id,
    // };
    axios.get(`api/questions/4`, {
      // params : params,
      headers: {
        "ngrok-skip-browser-warning": "69420"
      }
    })
    .then(res => {
      console.log(res)
      // setItem(res.data.items)
    })
    .catch(err => {
      console.error(err)
    })
  }, [])

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
            <QuestionDetail item={item} id={paramsId.id}/>
            <AnswerList item={item} id={paramsId.id}/>
          </div>
          <div className='detailPageAside'>
            <Aside />
          </div>
        </div>
      </div>
    </div>
  )
}
