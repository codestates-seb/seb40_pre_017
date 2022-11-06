import React, {useState, useEffect} from 'react'
import QuestionList from '../components/js/questionPage/QuestionList'
import { Link } from 'react-router-dom'
import './searchPage.scss'
import Pagination from '../components/js/questionPage/Pagination';
import Aside from '../components/js/aside/Aside';
import Category from '../components/js/category/Category';
import axios from 'axios';
import NoSearch from '../components/js/noSearch/NoSearch';

export default function SearchPage({inputData}) {

  const [items, seItems] = useState(null);
  const [page, setPage] = useState(1);
  const [pageInfo, setPageInfo] = useState();

  useEffect(()=>{
    // let params = {
    //   "q" : inputData,
    //   "page" : 1
    // };
    // console.log(params)

    // axios.get('/api/search', {
    //   params : params,
    //   headers: {
    //     "ngrok-skip-browser-warning": "69420"
    //   }
    // })
    // .then(res => {
    //   console.log(res.data.items)
    //   seItems(res.data.items)
    // })
    // .catch(err => {
    //   console.error(err)
    // })
    let params = {
      "q": inputData,
      "page": page
    };
    
    let query = Object.keys(params)
    .map(k => encodeURIComponent(k) + '=' + encodeURIComponent(params[k]))
    .join('&');
    
    let url = '/api/search?' + query;

    fetch(url, {
      method: "GET",
      headers: new Headers({
        "ngrok-skip-browser-warning": "69420",
      }),
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
        {pageInfo && <Pagination page={page} setPage={setPage} pageInfo={pageInfo}/>}
      </div>
      <div className='questionPageAside'>
        <Aside />
      </div>
    </div>
    
  )
}
