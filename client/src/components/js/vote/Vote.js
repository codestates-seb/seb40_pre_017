import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { useLocation, useNavigate } from 'react-router-dom';
import '../../css/vote/Vote.scss'

const REACT_APP_API_URL = process.env.REACT_APP_API_URL;

export default function Vote({item, type, id, answerId, accessToken}) {
  axios.defaults.headers.common["Authorization"] = window.sessionStorage.getItem("jwtToken");

  axios.defaults.withCredentials = true;

  const location = useLocation();

  let navigate = useNavigate();

  // 투표된상태확인
  // const [ voteInfo, setVoteInfo ] = useState(null);
  let voteInfo = '';
  
  useEffect(() => {
    axios.get(`${REACT_APP_API_URL}questions/${id}/votes`)
    .then((res) => {
      voteInfo = res.data;
      if(type === 'question'){
        if(voteInfo.questionUpVote)setClickUp(true)
        else if(voteInfo.questionDownVote)setClickDown(true)
      }
      else if( type === 'answer'){
        let answerVote = voteInfo.answerVoteStates.filter(el => el.answerId === answerId);
        if(answerVote[0].answerUpVote){
          setClickUp(true)
        }
        else if(answerVote[0].answerDownVote)setClickDown(true)
      }
    })
    .catch(error => {
      if(error.response.data.message !== 'None Still Voted'){
        console.log(error.response);
      }
    });
  },[])

  // 투표찬성
  const [clickUp, setClickUp] = useState(false);
  const handleUp = () => {
    if(!clickDown){
      if(!clickUp){
        setClickUp(true);
        if(type === 'question'){
        //질문투표찬성
          axios.post(`${REACT_APP_API_URL}question/${id}/upvote`)
          .then((res) => {
            console.log(res);
            sessionStorage.setItem("redirect", location.pathname + location.search);
            navigate(`/dummy`)
          })
          .catch(error => {
            if(error.response.data.message === 'Cannot Vote Your Own Post'){
              alert('Cannot Vote Your Own Post')
            }
          });
        }else if(type === 'answer'){
          //답변투표찬성
          axios.post(`${REACT_APP_API_URL}question/${id}/answer/${answerId}/upvote`)
          .then((res) => {
            console.log(res)
            sessionStorage.setItem("redirect", location.pathname + location.search);
            navigate(`/dummy`)
          })
          .catch(error => {
            if(error.response.data.message === 'Cannot Vote Your Own Post'){
              alert('Cannot Vote Your Own Post')
            }
          });
        }
      }else if(clickUp){
        setClickUp(false);
        if(type === 'question'){
          //질문투표찬성 취소
          axios.post(`${REACT_APP_API_URL}question/${id}/upvote/undo`)
          .then((res) => {
            console.log(res)
            sessionStorage.setItem("redirect", location.pathname + location.search);
            navigate(`/dummy`)
          })
          .catch(error => {
            console.log(error.response);
          });
        }else if(type === 'answer'){
          //답변투표찬성 취소
          axios.post(`${REACT_APP_API_URL}question/${id}/answer/${answerId}/upvote/undo`)
          .then((res) => {
            console.log(res)
            sessionStorage.setItem("redirect", location.pathname + location.search);
            navigate(`/dummy`)
          })
          .catch(error => {
            console.log(error.response);
          });
        }
      }
    }
  }
  
  
  // 투표반대
  const [clickDown, setClickDown] = useState(false);
  const handleDown = () => {
    if(!clickUp){
      if(!clickDown){
        setClickDown(true);
        if(type === 'question'){
          //질문투표반대
          axios.post(`${REACT_APP_API_URL}question/${id}/downvote`)
          .then((res) => {
            console.log(res)
            sessionStorage.setItem("redirect", location.pathname + location.search);
            navigate(`/dummy`)
          })
          .catch(error => {
            if(error.response.data.message === 'Cannot Vote Your Own Post'){
              alert('Cannot Vote Your Own Post')
            }
          });
        }else if(type === 'answer'){
          //답변투표반대
          axios.post(`${REACT_APP_API_URL}question/${id}/answer/${answerId}/downvote`)
          .then((res) => {
            console.log(res)
            sessionStorage.setItem("redirect", location.pathname + location.search);
            navigate(`/dummy`)
          })
          .catch(error => {
            if(error.response.data.message === 'Cannot Vote Your Own Post'){
              alert('Cannot Vote Your Own Post')
            }
          });
        }
      }else if(clickDown){
        setClickDown(false);
        if(type === 'question'){
          //질문투표반대 취소
          axios.post(`${REACT_APP_API_URL}question/${id}/downvote/undo`)
          .then((res) => {
            console.log(res)
            sessionStorage.setItem("redirect", location.pathname + location.search);
            navigate(`/dummy`)
          })
          .catch(error => {
            console.log(error.response);
          });
        }else if(type === 'answer'){
          //답변투표반대 취소
          axios.post(`${REACT_APP_API_URL}question/${id}/answer/${answerId}/downvote/undo`)
          .then((res) => {
            console.log(res)
            sessionStorage.setItem("redirect", location.pathname + location.search);
            navigate(`/dummy`)
          })
          .catch(error => {
            console.log(error.response);
          });
        }
      }
    }
  }

  return (
    <div className='voteWrap'>
      <button className={ clickUp ? 'voteUp clickUp' : 'voteUp'} onClick={handleUp}></button> 
      <div className='voteCount'>{item}</div>
      <button className={ clickDown ? 'voteDown clickDown' : 'voteDown'} onClick={handleDown}></button>
      <div className='etcBtn'>
        <i className="fa-regular fa-bookmark"></i>
        <i className="fa-solid fa-clock-rotate-left"></i>
      </div>
    </div>
  )
}
