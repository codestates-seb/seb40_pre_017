import React from 'react'
import AnswerList from '../components/js/answer/AnswerList'
import QuestionDetail from '../components/js/question/QuestionDetail'
import { useParams } from 'react-router-dom'

export default function DetailPage({items}) {
  //오류! 새로고침시 useParams가 안불러짐
  
  //id 파라미터 가져오기
  let params  = useParams();
  
  //params로 questionData 가져오기
  let item = items.filter(item => (
    item.question.questionId === Number(params.id)
  ))

  return (
    <div>
        <QuestionDetail item={item[0]}/>
        <AnswerList />
    </div>
  )
}
