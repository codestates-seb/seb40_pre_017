import React, { useState } from 'react'
import Background from '../assets/imgs/Background.svg'
import './AddQuestion.scss'
import Inputbox from '../components/js/addContent/Inputbox'
import { fetchCreate } from '../util/api'
import  useFetch  from '../util/useFetch'

export default function AddQuestion() {
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


  const handleSubmit = (e) => {
    e.preventDefault();

    // data 생성 & POST (임시)
    let data = {
    tags,
    member,
    "question": {
        "isAnswered": false,
        "viewCount": 0,
        "acceptedAnswerId": 55152722,
        "answerCount": 0,
        "votes": 0,
        "createdAt": 1552344257,
        "modifiedAt": 1596034268,
        "questionId": 55111502,
        "link": "https://stackoverflow.com/questions/55111503/convert-a-gdoc-into-image",
        title,
        content,
        "qcomment": []
    },
    "answer": []
    }
    fetchCreate("http://localhost:3001/items/", data)

    // data 생성 & POST (Api)
    // let data = { title, content, tags }
    // fetchCreate("/questions/", data)
  }

  return (
    <div className='addOut'>
      <div className='addQuestion'>
        <div className='addHeadWrap'>
          <img src={Background} alt='backgroundImg'/>
          <h1>Ask a public question</h1>
        </div>
        
        <div className='addContent'>
          <div className='addGuide'>
            Writing a good question
            You’re ready to ask a programming-related question and this form will help guide you through the process.

            Looking to ask a non-programming question? See the topics here to find a relevant site.

            Steps
            Summarize your problem in a one-line title.
            Describe your problem in more detail.
            Describe what you tried and what you expected to happen.
            Add “tags” which help surface your question to members of the community.
            Review your question and post it to the site.
          </div>
          <Inputbox setTitle={setTitle} setContent={setContent} tags={tags} setTags={setTags}/>
          <button onClick={handleSubmit}>Review your question</button>
        </div>
        
      </div>
    </div>
  )
}
