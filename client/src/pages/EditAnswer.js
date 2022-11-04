import axios from 'axios';
import React, { useRef } from 'react'
import { Link, useLocation,  useNavigate,  useParams } from 'react-router-dom'
import AddContent from '../components/js/addContent/AddContent';
import './EditAnswer.scss'

export default function EditAnswer({accessToken}) {
    axios.defaults.headers.common["Authorization"] = accessToken;


    //id 파라미터 가져오기
    let params  = useParams();
    const navigate = useNavigate();
    const location = useLocation();
    const { answer, item } = location.state;

    const content = answer.content;
    const contentInput = useRef();

    const handleEdit = (e) => {
        e.preventDefault();
  
    // data 생성 & Patch (Api)
        let data = { content: contentInput.current.getInstance().getMarkdown() }
        axios.patch(`/question/${params.id}/answer/${params.answerId}`, data)
        .then((res) => {
            navigate(`/questions/${item.question.questionId}`)
            // window.location.replace(`/questions/${item.question.questionId}`)
        })
        .catch(error => {
            console.log(error.response);
        });
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
