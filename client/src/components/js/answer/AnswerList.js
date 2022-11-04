import React, { useRef, useState } from 'react'
import Answer from './Answer'
import '../../css/answer/AnswerList.scss'
import AddContent from '../addContent/AddContent';
import axios from 'axios'
import { useNavigate, useLocation } from 'react-router-dom'


export default function AnswerList({item, accessToken}) {

  const navigate = useNavigate();
  const location = useLocation();
  
  axios.defaults.headers.common["Authorization"] = accessToken;

  let count = 0;
  if(item.answers !== []){
    count = item.answers.length;
  }

  // Add answer 
  const [ answerContent, setAnswerContent ] = useState('')
  const contentInput = useRef();

  const handleAddAnswer = (e) => {
    e.preventDefault();
    console.log(item)
    if(accessToken) {
      // data 생성 & POST (Api)
      let data = { content: contentInput.current.getInstance().getMarkdown() }
      axios.post(`/api/question/${item.question.questionId}/answer`, data)
      .then((res) => {
        console.log(res.data)
        // window.location.reload();
        window.location.replace(`/questions/${item.question.questionId}`)
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
        {/* <div>Sorted by:</div> */}
      </div>
      {item.answers && item.answers.map((answer) => (
        <div key={answer.answerId}>
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
