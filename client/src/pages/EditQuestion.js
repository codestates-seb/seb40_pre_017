import React from 'react'
import Inputbox from '../components/js/addContent/Inputbox'
import { useState } from 'react';
import { fetchPatch } from '../util/api';
import { useParams, Link } from 'react-router-dom';
import './EditQuestion.scss'
import Category from '../components/js/category/Category';
import Aside from '../components/js/aside/Aside';

export default function EditQuestion({items}) {
    //id 파라미터 가져오기
    let params  = useParams();
    
  //params로 questionData 가져오기
    let item = items.filter(item => (
    item.question.questionId === Number(params.id)
    ))[0]

    //제목
    const [title, setTitle] = useState(item.question.title);

  //content
    const [content, setContent] = useState(item.question.content);

  //tag
    const [tags, setTags] = useState(item.tags);

    const handleEdit = (e) => {
      e.preventDefault();

      // data 생성 & Patch (임시)
      let question = Object.assign(item.question);
      question.title = title;
      question.content = content;
      let data = {
      tags,
      question
      }
      fetchPatch("http://localhost:3001/items/", item.id, data);

      // data 생성 & Patch (Api)
      // let data = { title, content, tags }
      // fetchPatch("/questions", id, data)
      // fetchPatch api에 맞게 추후 수정
    }
    return (
      <div className='editQuestionWrap'>
        <div className='editQuestionNavbar'><Category/></div>
        <div className='editQuestionMain'>
          <h2>Question</h2>
          <Inputbox 
              setTitle={setTitle} 
              setContent={setContent} 
              tags={tags} 
              setTags={setTags} 
              title={title} 
              content={content}
          />
          <button onClick={handleEdit} className='saveEdit'>Save edits</button>
          <Link to={`/questions/${params.id}`}>
              <button className='cancel'>Cancel</button>
          </Link>
        </div>
        <div className='editQuestionAside'><Aside/></div>
      </div>
    )
}
