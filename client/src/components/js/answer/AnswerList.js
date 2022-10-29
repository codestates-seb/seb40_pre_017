import React, { useState } from 'react'
import Answer from './Answer'
import '../../css/answer/AnswerList.scss'
import AddContent from '../addContent/AddContent';
import { fetchPatch } from '../../../util/api';

export default function AnswerList({item}) {
  let count = 0;
  if(item.answer !== []){
    count = item.answer.length;
  }

  // Add answer 
  const [ answerContent, setAnswerContent ] = useState('')

  const handleAddAnswer = (e) => {
    e.preventDefault();

    // data 생성 & POST (임시)
    let answer = [
      {
        "answerId": 21187802,
        "memberId": 21123802,
        "createdAt": 1552234257,
        "modifiedAt": 1523034268,
        content: answerContent,
        "votes": 0,
        "isAccepted": "true",
        "reputation": 23,
        "profileImage": "https://lh4.googleusercontent.com/-8o1Zs4lQprY/AAAAAAAAAAI/AAAAAAAAAAA/ACHi3rebPfyBOjde6DPW0bjTb2M1BSIwCg/mo/photo.jpg?sz=256",
        "userName": "fulvio",
        "link": "https://stackoverflow.com/users/11187800/fulvio",
        "aComments": [
        ]
      }
    ]
    fetchPatch("http://localhost:3001/items/",item.id, { answer })

    // data 생성 & POST (Api)
    // let data = { content: answerContent }
    // fetchCreate(`/questions/${id}/answer`, data)
  }

  return (
    <div>
      <div className='answerHeadWrap'>
        <div className='answerCount'>{count} Answers</div>
        {/* <div>Sorted by:</div> */}
      </div>
      {item.answer && item.answer.map(answer => (
        <div key={answer.answerId}>
          <Answer answer={answer}/>
        </div>
      ))}
      <h1 className='yourAnswer'>Your Answer</h1>
      <AddContent 
      content={answerContent} 
      setContent={setAnswerContent}
      />
      <button className='postAnswerBtn' onClick={handleAddAnswer}>Post Your Answer</button>
    </div>
  )
}
