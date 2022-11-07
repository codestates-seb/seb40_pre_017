import React, { useState } from 'react'
import Vote from '../vote/Vote'
import ProfileAnswer from '../profile/ProfileAnswer'
import CommentList from '../comment/CommentList'
import '../../css/answer/Answer.scss'
import { useLocation, useNavigate } from 'react-router-dom'
import { Viewer } from '@toast-ui/react-editor';
import '@toast-ui/editor/dist/toastui-editor-viewer.css';
import axios from 'axios'
// import { fetchCreate, fetchDelete } from '../../../util/api'

const REACT_APP_API_URL = process.env.REACT_APP_API_URL;

export default function Answer({answer, id, answerId, item, accessToken}) {
  axios.defaults.headers.common["Authorization"] = window.sessionStorage.getItem("jwtToken");
  axios.defaults.withCredentials = true;

  const navigate = useNavigate();
  const location = useLocation();

  // 답변 삭제
  const handleDelete = () => {
    // api DELETE
    if(window.confirm("Are you sure you want to delete the answer?") === true) {
      axios.delete(`${REACT_APP_API_URL}question/${item.question.questionId}/answer/${answerId}`)
      .then((res) => {
        sessionStorage.setItem("redirect", location.pathname + location.search);
        navigate(`/dummy`)
      })
      .catch(error => {
        console.log(error.response);
      });
    }
  }

  //답변 채택
  const [ check, setCheck ] = useState(answer.accepted);
  const handleCheck = () => {
    if(!check){
      axios.post(`${REACT_APP_API_URL}question/${id}/answer/${answerId}/accept`)
      .then((res) => {
        console.log(res)
      })
      .catch(error => {
        console.log(error.response);
      });
      setCheck(true);
    }else{
      axios.post(`${REACT_APP_API_URL}question/${id}/answer/${answerId}/accept/undo`)
      .then((res) => {
        console.log(res)
      })
      .catch(error => {
        console.log(error.response);
      });
      setCheck(false);
    }
  }

  //질문 수정 data GET
  const clickEdit = () => {
    navigate(`/questions/${id}/editanswer/${answerId}`, {
      state: {
        answer,
        item
      }
    })
  }

  return (
    <div className='answerWrap'>
      <div className='answerVote'>
        <Vote item={answer.voteCount} id={id} answerId={answerId} type={'answer'} accessToken={accessToken}/>
        <button 
          onClick={handleCheck} 
          className={check ? 'select answerCheck' : 'select'}
        ><i className="fa-solid fa-check"></i></button>
      </div>
      
      <div className='answerMainWrap'>
        <h3><Viewer initialValue={answer.content}/></h3>
        <div className='detailBottomWrap'>
          <div className='detailEditWrap'>
            <button>Share</button>
            {JSON.parse(window.sessionStorage.getItem("member")) !== null && (answer.answerMember.username === JSON.parse(window.sessionStorage.getItem("member")).username &&
              <>
                <button onClick={clickEdit}>Edit</button>
                <button onClick={handleDelete}>Delete</button>
              </>
            )}
          </div>
          <ProfileAnswer item={answer} time={answer.createdAt}/>
        </div>
        <CommentList item={answer.answerComments} id={id} answerId={answerId} type={'answer'} accessToken={accessToken}/>
      </div>
    </div>
  )
}
