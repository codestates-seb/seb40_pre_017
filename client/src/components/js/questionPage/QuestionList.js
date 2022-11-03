import React from 'react'
import Question from './Question'
import '../../css/questionPage/QuestionList.scss'

export default function QuestionList({items}) {
  return (
    <div className='questionList'>
        {items && items.map(item => (
        <div key={item.question.questionId}>
          <Question item={item}/>
        </div>
      ))}
    </div>
  )
}
