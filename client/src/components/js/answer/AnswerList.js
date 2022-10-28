import React from 'react'
import Answer from './Answer'
import '../../css/answer/AnswerList.scss'

export default function AnswerList({item}) {
  let count = 0;
  if(item.answer !== []){
    count = item.answer.length;
  }
  return (
    <div>
      <div className='answerHeadWrap'>
        <div className='answerCount'>{count} Answers</div>
        <div>Sorted by:</div>
      </div>
      {item.answer && item.answer.map(answer => (
        <div key={answer.answerId}>
          <Answer answer={answer}/>
        </div>
      ))}
    </div>
  )
}
