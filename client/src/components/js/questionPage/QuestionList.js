import React from 'react'
import Question from './Question'

export default function QuestionList({items}) {
  return (
    <div>QuestionList
        {items && items.map(item => (
        <div key={item.question.question_id}>
          <Question item={item}/>
        </div>
      ))}
    </div>
  )
}
