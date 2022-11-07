import React, { useRef, useState } from 'react'
import Background from '../assets/imgs/Background.svg'
import './AddQuestion.scss'
import Inputbox from '../components/js/addContent/Inputbox'
import '@toast-ui/editor/dist/toastui-editor.css';
import { Editor } from '@toast-ui/react-editor';
import axios from 'axios'
import { useNavigate } from 'react-router-dom'

const REACT_APP_API_URL = process.env.REACT_APP_API_URL;

export default function AddQuestion({accessToken}) {
  axios.defaults.headers.common["Authorization"] = window.sessionStorage.getItem("jwtToken");
  axios.defaults.withCredentials = true;

  //제목
  const [title, setTitle] = useState('');

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
    
    axios.post(`${REACT_APP_API_URL}questions`, data)
    .then((res) => {
      console.log(res.data)
      navigate(`/questions/${res.data}`)
    })
    .catch(error => {
      console.log(error.response);
      navigate('/')
    });
  }

  return (
    <div className='addOut'>
      <div className='addQuestion'>
        <div className='addHeadWrap'>
          <h1>Ask a public question</h1>
          <img src={Background} alt='backgroundImg'/>
        </div>
        
        <div className='addContent'>
          <div className='guideWrap'>
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
            {/* <div className='blank'>asdfasdfasfasdfasdfasdfasdfasdfsdfsdfsfda</div> */}
          </div>
          <Inputbox 
            setTitle={setTitle} 
            tags={tags} 
            setTags={setTags} 
            setSubmitDis={setSubmitDis}
            contentInput={contentInput}
            type={'add'}
            accessToken={accessToken}
          />
          <button className='blueBtn' onClick={handleSubmit} disabled={submitDis ? true : false}>Review your question</button>
        </div>
        
      </div>
    </div>
  )
}
