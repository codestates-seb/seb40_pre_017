import React, {useState, useEffect} from 'react'
import QuestionList from '../components/js/questionPage/QuestionList'
import { Link } from 'react-router-dom'
import './QuestionPage.scss'
import Pagination from '../components/js/questionPage/Pagination';
import Aside from '../components/js/aside/Aside';
import Category from '../components/js/category/Category';
import axios from 'axios';

export default function QuestionPage({ filterData, changeFilterData}) {

  const [items, seItems] = useState(null);
  const [page, setPage] = useState(1);
  const [pageInfo, setPageInfo] = useState();

  useEffect(()=>{
    let params = {
      "filters" : filterData,
      "page" : page
    };
    axios.get('/api/questions', {
      params : params,
      headers: {
        "ngrok-skip-browser-warning": "69420"
      }
    })
    .then(res => {
      seItems(res.data.items)
      setPageInfo(res.data.pageInfo)
    })
    .catch(err => {
      console.error(err)
    })
  }, [filterData, page])


  //questionList Count
  let count = 0;
  if(items){
    count = items.length;
  }

  return (
    <div className='questionPageWrap'>
      <div className='questionPageNavbar'>
        <Category />
      </div>
      <div className='questionPage'>
        <div className='headAddWrap'>
          <h1>All Questions</h1>
          <Link to={'/add'}>
            <button>Ask Question</button>
          </Link>
        </div>
        <div className='countFilterWrap'>
          <span>{count} question</span>
          <div className='filterBtns'>
            <button className={'' + (filterData === "NoAnswer" && "active")} onClick={changeFilterData} name='NoAnswer'>NoAnswer</button>
            <button className={'' + (filterData === "NoAcceptedAnswer" && "active")} onClick={changeFilterData} name='NoAcceptedAnswer'>NoAcceptedAnswer</button>
          </div>
        </div>
        <QuestionList items={items}/>
        {pageInfo && <Pagination page={page} setPage={setPage} pageInfo={pageInfo}/>}
      </div>
      <div className='questionPageAside'>
        <Aside />
      </div>
    </div>
    
  )
}
