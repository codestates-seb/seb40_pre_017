import React, { useEffect, useRef, useState } from 'react'
import Background from '../assets/imgs/Background.svg'
import './AddQuestion.scss'
import Inputbox from '../components/js/addContent/Inputbox'
import { fetchCreate } from '../util/api'
import  useFetch  from '../util/useFetch'
import '@toast-ui/editor/dist/toastui-editor.css';
import { Editor } from '@toast-ui/react-editor';
import axios from 'axios'
import { useNavigate } from 'react-router-dom'

export default function AddQuestion({accessToken}) {
  //회원정보 받아오기 (임시)
  const [member] = useFetch("http://localhost:3001/member/");
  //회원정보 받아오기 (Api)
  // const [member] = useFetch("/users/{ids}");

  //제목
  const [title, setTitle] = useState('');

  //content
  const [content, setContent] = useState('');

  //tag
  const [tags, setTags] = useState([]);

  //submit 비활성화
  const [ submitDis, setSubmitDis ] = useState(true);


  const contentInput = useRef();

  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();

    // data 생성 & POST (Api)
    let data = { title, content: contentInput.current.getInstance().getMarkdown(), tags }
  //   let data = {
  //     title : 'sdfasdfasfdasdfasdfasdf',
  //     content : 'asdfddfasdfasdfasdfasdfasdfasdfㅁㄴㅇㅁㄴㅇㅁㄴㅇ',
  //     tags : [
  //         {name : 'java'},
  //         {name : 'python'}
  //     ]
  // }

    // fetchCreate("/questions/", data)
    fetch("/api/questions", {
      method: "POST",
      headers: new Headers({
        "Authorization": accessToken,
        "ngrok-skip-browser-warning": "69420",
        "Content-Type" : "application/json"
      }),
      body: JSON.stringify(data)
    })
    .then(res => {
      console.log(res)
    })
    .catch(err => {
      console.error(err)
    })
    navigate('/')
  }

  return (
    <div className='addOut'>
      <div className='addQuestion'>
        <div className='addHeadWrap'>
          <h1>Ask a public question</h1>
          <img src={Background} alt='backgroundImg'/>
        </div>
        
        <div className='addContent'>
          <div className='addGuide'>
            <h3>Writing a good question</h3>
            You’re ready to ask a programming-related question and this form will help guide you through the process.

            Looking to ask a non-programming question? See the topics here to find a relevant site.

            Steps
            Summarize your problem in a one-line title.
            Describe your problem in more detail.
            Describe what you tried and what you expected to happen.
            Add “tags” which help surface your question to members of the community.
            Review your question and post it to the site.
          </div>
          <Inputbox 
            setTitle={setTitle} 
            setContent={setContent} 
            tags={tags} 
            setTags={setTags} 
            setSubmitDis={setSubmitDis}
            contentInput={contentInput}
          />
          <button className='blueBtn' onClick={handleSubmit} disabled={submitDis ? true : false}>Review your question</button>
        </div>
        
      </div>
    </div>
  )
}
