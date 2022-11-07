import axios from 'axios';
import React from 'react'
import '../../css/comment/Comment.scss'
import createdAt from '../createdAt/CreatedAt';
import { useParams, useNavigate, useLocation } from 'react-router-dom';

const REACT_APP_API_URL = process.env.REACT_APP_API_URL;

export default function Comment({id, content, setEditClick, type, setEditValue, setCommentId, accessToken, answerId, setPastValue}) {
  let params  = useParams();
  const location = useLocation();

  let navigate = useNavigate();
  axios.defaults.headers.common["Authorization"] = window.sessionStorage.getItem("jwtToken");
  axios.defaults.withCredentials = true;

  // click edit
  const clickEdit = () => {
    setEditClick(true);
    setEditValue(content.content)
    if(type === 'question'){
      setCommentId(content.questionCommentId)
    }else if(type === 'answer'){
      setCommentId(content.answerCommentId)
    }
    
  }

  // click delete
  const handleDelete = () => {
    if(type === 'question'){
      if (window.confirm("Are you sure you want to delete the comment?") === true) {
        axios.delete(`${REACT_APP_API_URL}question/${id}/comments/${content.questionCommentId}`)
        .then((res) => {
          if(res.status === 200) {
            sessionStorage.setItem("redirect", location.pathname + location.search);
            navigate(`/dummy`)
          }
        })
        .catch(error => {
          console.log(error.response)
          alert(error.response.data.errors[0].reason);
        });
      }
    }else if(type === 'answer'){
      if (window.confirm("Are you sure you want to delete the comment?") === true) {
        axios.delete(`${REACT_APP_API_URL}question/${id}/answer/${answerId}/comments/${content.answerCommentId}`)
        .then((res) => {
          if(res.status === 200) {
            sessionStorage.setItem("redirect", location.pathname + location.search);
            navigate(`/dummy`)
          }
        })
        .catch(error => {
          console.log(error)
          alert(error.response.data.errors[0].reason);
        });
      }
    }
  }
  return (
    <div className='commentWrap'>
      <div className='commentContent'>{content.content}</div>
      <div className='commentName'>{content.userName}</div>
      <div className='commentTime'>{createdAt(content.createdAt)}</div>
      {JSON.parse(window.sessionStorage.getItem("member")) !== null && (content.userName === JSON.parse(window.sessionStorage.getItem("member")).username && 
      <>
        <i className="fa-solid fa-pencil" onClick={clickEdit}></i>
        <i className="fa-solid fa-trash-can" onClick={handleDelete}></i>
      </>
      )}
    </div>
  )
}
