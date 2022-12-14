import React, {useState, useEffect} from 'react'
import QuestionList from '../components/js/questionPage/QuestionList'
import { Link } from 'react-router-dom'
import './searchPage.scss'
import Pagination from '../components/js/questionPage/Pagination';
import Aside from '../components/js/aside/Aside';
import Category from '../components/js/category/Category';
import axios from 'axios';
import NoSearch from '../components/js/noSearch/NoSearch';

axios.defaults.withCredentials = true;
const REACT_APP_API_URL = process.env.REACT_APP_API_URL;

export default function SearchPage({inputData}) {

  const [items, seItems] = useState(null);
  const [page, setPage] = useState(1);
  const [pageInfo, setPageInfo] = useState();

  useEffect(()=>{
    let params = {
      "q": inputData,
      "page": page
    };
    
    let query = Object.keys(params)
    .map(k => encodeURIComponent(k) + '=' + encodeURIComponent(params[k]))
    .join('&');
    
    let url = `${REACT_APP_API_URL}search?` + query;

    fetch(url, {
      method: "GET",
      credentials: 'include'
    })
    .then((res) => {
      return res.json()
    })
    .then((resData) => {
      console.log(resData)
      seItems(resData.items)
      setPageInfo(resData.pageInfo)
    })
  }, [inputData, page])



  //questionList Count
  let count = 0;
  if(items){
    count = items.length;
  }
  console.log(items)
  return (
    <div className='questionPageWrap'>
      <div className='questionPageNavbar'>
        <Category />
      </div>
      <div className='questionPage'>
        <div className='headAddWrap'>
          <h1>Search Results</h1>
          <Link to={'/add'}>
            <button>Ask Question</button>
          </Link>
        </div>
        <div className='countFilterWrap'>
          <span>{count} question</span>
        </div>
        {!items ? <NoSearch /> : <QuestionList items={items}/>}
        <Pagination page={page} setPage={setPage} pageInfo={pageInfo}/>
      </div>
      <div className='questionPageAside'>
        <Aside />
      </div>
    </div>
    
  )
}
