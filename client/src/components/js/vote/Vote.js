import React, { useState } from 'react'
import { fetchCreate } from '../../../util/api';
import '../../css/vote/Vote.scss'

export default function Vote({item, type, id, accessToken}) {
  console.log(accessToken)
  // 투표찬성
  const [clickUp, setClickUp] = useState(false);
  const handleUp = () => {
    if(!clickDown){
      if(!clickUp){
        setClickUp(true);
        if(type === 'question'){
          //질문투표찬성
          ///questions/{id}/upvote
          // fetchCreate(`/api/questions/${id}/upvote`)
          fetch(`/api/questions/${id}/upvote`, {
            method: "POST",
            headers: new Headers({
              "Authorization": `${accessToken}`,
              "ngrok-skip-browser-warning": "69420",
              "Content-Type" : "application/json"
            })
          })
          .then(res => {
            console.log(res)
          })
          .catch(err => {
            console.error(err)
          })
        }else if(type === 'answer'){
          //답변투표찬성
          ///question/{id}/answer/{answer-id}/upvote
        }
        
      }else if(clickUp){
        setClickUp(false);
        if(type === 'question'){
          //질문투표찬성 취소
          ///questions/{id}/upvote/undo
        }else if(type === 'answer'){
          //답변투표찬성 취소
          ///question/{id}/answer/{answer-id}/upvote/undo
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
          ///questions/{id}/downvote
        }else if(type === 'answer'){
          //답변투표반대
          ///question/{id}/answer/{answer-id}/downvote
        }
      }else if(clickDown){
        setClickDown(false);
        if(type === 'question'){
          //질문투표반대 취소
          ///questions/{id}/downvote/undo
        }else if(type === 'answer'){
          //답변투표반대 취소
          ///question/{id}/answer/{answer-id}/downvote/undo
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
