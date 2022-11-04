import axios from 'axios';
import React, { useEffect, useState } from 'react'
import '../../css/vote/Vote.scss'

export default function Vote({item, type, id, answerId, accessToken}) {
  axios.defaults.headers.common["Authorization"] = accessToken;

  // 투표된상태확인
  const [voteInfo, setVoteInfo ] = useState();
  useEffect(() => {
    axios.get(`/api/questions/${id}/votes`)
    .then((res) => {
      setVoteInfo(res.data)
    })
    .catch(error => {
      console.log(error.response);
    });

    if(type === 'question'){
      if(voteInfo.questionUpVote)setClickUp(true)
      else if(voteInfo.questionDownVote)setClickDown(true)
    }else if( type === 'answer'){
      let answerVote = voteInfo.answerVoteStates.filter(el => el.answerId === answerId);
      if(answerVote.answerUpVote)setClickUp(true)
      else if(answerVote.answerDownVote)setClickDown(true)
    }
  },[])

  // 투표찬성
  const [clickUp, setClickUp] = useState(false);
  const handleUp = () => {
    if(!clickDown){
      if(!clickUp){
        setClickUp(true);
        if(type === 'question'){
          //질문투표찬성
          ///questions/{id}/upvote
          axios.post(`/api/question/${id}/upvote`)
          .then((res) => {
            console.log(res)
          })
          .catch(error => {
            console.log(error.response);
          });
        }else if(type === 'answer'){
          //답변투표찬성
          ///question/{id}/answer/{answer-id}/upvote
          axios.post(`/api/question/${id}/answer/${answerId}/upvote`)
          .then((res) => {
            console.log(res)
          })
          .catch(error => {
            console.log(error.response);
          });
        }
        // window.location.replace(`/questions/${id}`)
      }else if(clickUp){
        setClickUp(false);
        if(type === 'question'){
          //질문투표찬성 취소
          ///questions/{id}/upvote/undo
          axios.post(`/api/question/${id}/upvote/undo`)
          .then((res) => {
            console.log(res)
          })
          .catch(error => {
            console.log(error.response);
          });
        }else if(type === 'answer'){
          //답변투표찬성 취소
          ///question/{id}/answer/{answer-id}/upvote/undo
          axios.post(`/api/question/${id}/answer/${answerId}/upvote/undo`)
          .then((res) => {
            console.log(res)
          })
          .catch(error => {
            console.log(error.response);
          });
        }
        // window.location.replace(`/questions/${id}`)
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
          ///questions/{id}/downvote
          axios.post(`/api/question/${id}/downvote`)
          .then((res) => {
            console.log(res)
          })
          .catch(error => {
            console.log(error.response);
          });
        }else if(type === 'answer'){
          //답변투표반대
          ///question/{id}/answer/{answer-id}/downvote
          axios.post(`/api/question/${id}/answer/${answerId}/downvote`)
          .then((res) => {
            console.log(res)
          })
          .catch(error => {
            console.log(error.response);
          });
        }
        // window.location.replace(`/questions/${id}`)
      }else if(clickDown){
        setClickDown(false);
        if(type === 'question'){
          //질문투표반대 취소
          ///questions/{id}/downvote/undo
          axios.post(`/api/question/${id}/downvote/undo`)
          .then((res) => {
            console.log(res)
          })
          .catch(error => {
            console.log(error.response);
          });
        }else if(type === 'answer'){
          //답변투표반대 취소
          ///question/{id}/answer/{answer-id}/downvote/undo
          axios.post(`/api/question/${id}/answer/${answerId}/downvote/undo`)
          .then((res) => {
            console.log(res)
          })
          .catch(error => {
            console.log(error.response);
          });
        }
        // window.location.replace(`/questions/${id}`)
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
