import React, {useState, useEffect} from 'react'
import QuestionList from '../components/js/questionPage/QuestionList'
import { Link, useNavigate, useLocation } from 'react-router-dom'
import './QuestionPage.scss'
import Pagination from '../components/js/questionPage/Pagination';
import Aside from '../components/js/aside/Aside';
import Category from '../components/js/category/Category';
import axios from 'axios';

const REACT_APP_API_URL = process.env.REACT_APP_API_URL;
axios.defaults.withCredentials = true;

export default function QuestionPage({accessToken, filterData, changeFilterData, clickFilter,page,setPage}) {

  const [items, seItems] = useState(null);
  // const [page, setPage] = useState(1);
  const [pageInfo, setPageInfo] = useState();

  const navigate = useNavigate();
  const location = useLocation();

  useEffect(()=>{
    let params = {
      "filters" : filterData,
      "page" : page
    };
    axios.get(`${REACT_APP_API_URL}questions`, {
      params : params,
      headers: {
        "ngrok-skip-browser-warning": "69420"
      }
    })
    .then(res => {
      seItems(res.data.items)
      setPageInfo(res.data.pageInfo.totalElements)
    })
    .catch(err => {
      console.error(err)
    })
  }, [filterData, page]);
  
  const createQuestion = () => {
    if(window.sessionStorage.getItem("jwtToken")) {
      navigate("/add")
    }else{
      alert('This service requires login')
      localStorage.setItem("lastPath", `${location.pathname}`);
      navigate("/login")
    }
  }

  return (
    <div className='questionPageWrap'>
      <div className='questionPageNavbar'>
        <Category />
      </div>
      <div className='questionPage'>
        <div className='headAddWrap'>
          <h1>All Questions</h1>
          <button onClick={createQuestion}>Ask Question</button>
        </div>
        <div className='countFilterWrap'>
          {pageInfo && <span>{pageInfo} questions</span>}
          <div className='filterBtns'>
            <button className={'' + (filterData === "NoAnswer" && "active")} onClick={changeFilterData} name='NoAnswer'>NoAnswer</button>
            <button className={'' + (filterData === "NoAcceptedAnswer" && "active")} onClick={changeFilterData} name='NoAcceptedAnswer'>NoAcceptedAnswer</button>
          </div>
        </div>
        <QuestionList items={items}/>
        {pageInfo && <Pagination page={page} setPage={setPage} pageInfo={pageInfo} clickFilter={clickFilter}/>}
      </div>
      <div className='questionPageAside'>
        <Aside />
      </div>
    </div>
    
  )
}
