import React, { useRef } from 'react'
import Inputbox from '../components/js/addContent/Inputbox'
import { useState } from 'react';
import { useParams, Link, useLocation, useNavigate } from 'react-router-dom';
import './EditQuestion.scss'
import Category from '../components/js/category/Category';
import Aside from '../components/js/aside/Aside';
import axios from 'axios';
import EditAside from '../components/js/aside/EditAside';

const REACT_APP_API_URL = process.env.REACT_APP_API_URL;


export default function EditQuestion({accessToken}) {
  axios.defaults.headers.common["Authorization"] = accessToken;
  axios.defaults.withCredentials = true;

  //id 파라미터 가져오기
  let params  = useParams();

  const navigate = useNavigate();
  const location = useLocation();
  const { item } = location.state;

  //제목
  const [title, setTitle] = useState(item.question.title);

  //content
  const content = item.question.summary
  const contentInput = useRef();

  //tag
  const [tags, setTags] = useState(item.tags);

  const handleEdit = (e) => {
    e.preventDefault();
    console.log(accessToken)

    // data 생성 & Patch (Api)
    let data = { title, content:contentInput.current.getInstance().getMarkdown(), tags }

    axios.patch(`${REACT_APP_API_URL}questions/${item.question.questionId}`, data)
    .then((res) => {
      // navigate(`/questions/${item.question.questionId}`)
      // window.location.replace(`/questions/${item.question.questionId}`)
      sessionStorage.setItem("redirect", `/questions/${item.question.questionId}`);
      navigate(`/dummy`)
    })
    .catch(error => {
      console.log(error.response);
    });
  }
  return (
    <div className='editQuestionWrap'>
        <div className='editQuestionNavbar'><Category/></div>
        <div className='editQuestionMain'>
          <h2>Question</h2>
          <Inputbox 
              setTitle={setTitle} 
              // setContent={setContent} 
              tags={tags} 
              setTags={setTags} 
              title={title} 
              content={content}
              contentInput={contentInput}
          />
          <button onClick={handleEdit} className='saveEdit'>Save edits</button>
          <Link to={`/questions/${params.id}`}>
              <button className='cancel'>Cancel</button>
          </Link>
        </div>
        <div className='editQuestionAside'><EditAside/></div>
    </div>
  )
}
