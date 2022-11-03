import React, { useEffect, useState } from 'react'
import CommentList from '../comment/CommentList'
import Profile from '../profile/Profile'
import Vote from '../vote/Vote'
import Tags from '../tags/Tags'
import '../../css/question/QuestionDetail.scss'
import { Link } from 'react-router-dom'
import { Viewer } from '@toast-ui/react-editor';
import axios from 'axios'
import { useNavigate } from 'react-router-dom'

import '@toast-ui/editor/dist/toastui-editor-viewer.css';



export default function QuestionDetail({item, id, accessToken}) {
  
  axios.defaults.headers.common["Authorization"] = accessToken;

  // 삭제
  const handleDelete = () => {
    axios.delete(`/api/questions/${id}`)
    .then((res) => {
      console.log(res)
      // navigate(`/question${res}`)
      // return res.json()
    })
    .catch(error => {
      console.log(error.response);
    });
    // navigate('/')
  }

  // edit 데이터 GET
  // const [detailItem, setDetailItem] = useState();
  // useEffect(()=>{
  //   axios.get(`/api/questions/${item.question.questionId}`, {
  //     headers: {
  //       "ngrok-skip-browser-warning": "69420"
  //     }
  //   })
  //   .then(res => {
  //     setDetailItem(res.data)
  //   })
  //   .catch(err => {
  //     console.error(err)
  //   })
  // }, [item])

  const navigate = useNavigate();
  const clickEdit = () => {
    navigate(`/questions/${id}/edit`, {
      state: {item}
    })
  }

  return (
    <div className='questionDetail'>
      <Vote item={item.question.voteCount} type={'question'} id={item.question.questionId} accessToken={accessToken}/>
      <div className='detailMainWrap'>
        <div className='detailContent'>
          <h3><Viewer initialValue={item.question.summary}/></h3>
          <Tags item={item}/>
        </div>

        <div className='detailBottomWrap'>
          <div className='detailEditWrap'>
            <button>Share</button>
              <button onClick={clickEdit}>Edit</button>
            <button onClick={handleDelete}>Delete</button>
          </div>
          <Profile item={item.member}/>
        </div>
        <CommentList item={item.question.qcomment} type={'question'} temporary={item}/>
      </div>      
    </div>
  )
}
