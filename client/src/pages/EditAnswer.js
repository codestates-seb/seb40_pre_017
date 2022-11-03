import React, { useRef, useState } from 'react'
import { Link, useParams } from 'react-router-dom'
import AddContent from '../components/js/addContent/AddContent';
import { fetchPatch } from '../util/api';
import './EditAnswer.scss'

export default function EditAnswer({items}) {
    //id 파라미터 가져오기
    let params  = useParams();
    
    //params로 questionData 가져오기
    let item = items.filter(item => (
    item.question.questionId === Number(params.id)
    ))[0]
    
    //params로 answerData 가져오기
    let answerItem = item.answer.filter(el => (
        el.answerId === Number(params.answerId)
    ))

     //content
    //  const [content, setContent] = useState(answerItem.content);
    const content = answerItem.content;
    const contentInput = useRef();


    const handleEdit = (e) => {
        e.preventDefault();
  
    //data 생성 & Patch (임시)
        let answer = item.answer.slice();
        answer[0].content = contentInput.current.getInstance().getMarkdown();
        let data = { answer }
        fetchPatch("http://localhost:3001/items/", item.id, data);
  
    // data 생성 & Patch (Api)
        // let data = {  content }
        // fetchPatch(`/question/${params.id}/answer/${params.answerId}`, data)
        // fetchPatch api에 맞게 추후 수정
    }

  return (
    <div className='editAnswerWrap'>
        <h2>Answer</h2>
        <AddContent 
        content={content}
        // setContent={setContent} 
        contentInput={contentInput} 
        />
        <button onClick={handleEdit} className='saveEdit'>Save Edits</button>
        <Link to={`/questions/${params.id}`}>
            <button className='cancel'>Cancel</button>
        </Link>
      </div>
  )
}
