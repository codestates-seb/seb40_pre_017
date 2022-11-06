import CommentList from '../comment/CommentList'
import Profile from '../profile/Profile'
import Vote from '../vote/Vote'
import Tags from '../tags/Tags'
import '../../css/question/QuestionDetail.scss'
import { Viewer } from '@toast-ui/react-editor';
import axios from 'axios'
import { useNavigate } from 'react-router-dom'

import '@toast-ui/editor/dist/toastui-editor-viewer.css';

const REACT_APP_API_URL = process.env.REACT_APP_API_URL;

export default function QuestionDetail({item, id, accessToken }) {
  axios.defaults.headers.common["Authorization"] = accessToken;
  axios.defaults.withCredentials = true;

  // 삭제
  const handleDelete = () => {
    if (window.confirm("Are you sure you want to delete the question?") === true) {
      axios.delete(`${REACT_APP_API_URL}questions/${id}`)
      .then((res) => {
        console.log(res)
        navigate(`/`)
      })
      .catch(error => {
        console.log(error.response);
      });
      // navigate('/')
    }
  }

  // Edit 데이터 GET
  const navigate = useNavigate();
  const clickEdit = () => {
    navigate(`/questions/${id}/edit`, {
      state: {item}
    })
  }

  return (
    <div className='questionDetail'>
      <Vote item={item.question.voteCount} type={'question'} id={item.question.questionId} accessToken={accessToken}/>
      <div className='detailMainWrap'>
        <div className='detailContent'>
          <h3><Viewer initialValue={item.question.summary}/></h3>
          <Tags item={item}/>
        </div>

        <div className='detailBottomWrap'>
          <div className='detailEditWrap'>
            <button>Share</button>
            {JSON.parse(window.sessionStorage.getItem("member")) !== null && (item.member.username === JSON.parse(window.sessionStorage.getItem("member")).username && 
            <>
              <button onClick={clickEdit}>Edit</button>
              <button onClick={handleDelete}>Delete</button>
            </>
            )}
          </div>
          <Profile item={item.member} time={item.question.createdAt}/>
        </div>
        <CommentList item={item.questionComments} id={item.question.questionId} type={'question'} accessToken={accessToken}/>
      </div>
    </div>
  )
}
