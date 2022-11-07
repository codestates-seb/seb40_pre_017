import React, { useRef, useState } from 'react'
import Answer from './Answer'
import '../../css/answer/AnswerList.scss'
import AddContent from '../addContent/AddContent';
import axios from 'axios'
import { useNavigate, useLocation } from 'react-router-dom'

const REACT_APP_API_URL = process.env.REACT_APP_API_URL;
axios.defaults.headers.common["Authorization"] = window.sessionStorage.getItem("jwtToken");
axios.defaults.withCredentials = true;

export default function AnswerList({item, accessToken}) {

  const navigate = useNavigate();
  const location = useLocation();
  

  let count = 0;
  if(item.answers !== []){
    count = item.answers.length;
  }

  // Add answer 
  const [ answerContent, setAnswerContent ] = useState('')
  const contentInput = useRef();

  const handleAddAnswer = (e) => {
    e.preventDefault();

    if(window.sessionStorage.getItem("jwtToken")) {
      // data 생성 & POST (Api)
      let data = { content: contentInput.current.getInstance().getMarkdown() }
      axios.post(`${REACT_APP_API_URL}question/${item.question.questionId}/answer`, data)
      .then((res) => {
        sessionStorage.setItem("redirect", location.pathname + location.search);
        navigate(`/dummy`)
      })
      .catch(error => {
        console.log(error.response);
      });
    }else{
      alert('This service requires login')
      localStorage.setItem("lastPath", `${location.pathname}`);
      navigate("/login")
    }
  }

  return (
    <div>
      <div className='answerHeadWrap'>
        <div className='answerCount'>{count} Answers</div>
      </div>
      {item.answers && item.answers.map((answer, idx) => (
        <div key={idx}>
          <Answer 
            answer={answer} 
            id={item.question.questionId} 
            answerId={answer.answerId} 
            item={item}
            accessToken={accessToken}
          />
        </div>
      ))}
      <h1 className='yourAnswer'>Your Answer</h1>
      <div className='addAnswerInput'>
        <AddContent 
        content={answerContent} 
        setContent={setAnswerContent}
        type={'answer'}
        contentInput={contentInput}
        />
      </div>
      <button className='postAnswerBtn' onClick={handleAddAnswer}>Post Your Answer</button>
    </div>
  )
}
