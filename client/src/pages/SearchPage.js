import React, {useState, useEffect} from 'react'
import QuestionList from '../components/js/questionPage/QuestionList'
import { Link } from 'react-router-dom'
import './searchPage.scss'
// import Pagination from '../components/js/questionPage/Pagination';
import ReactPaginate from 'react-paginate'
import Aside from '../components/js/aside/Aside';
import Category from '../components/js/category/Category';
import axios from 'axios';
import NoSearch from '../components/js/noSearch/NoSearch';

axios.defaults.withCredentials = true;
const REACT_APP_API_URL = process.env.REACT_APP_API_URL;

export default function SearchPage({inputData, handlePageChange, pagetest}) {

  const [items, seItems] = useState(null);
  const [pageInfo, setPageInfo] = useState();

  useEffect(()=>{
    let params = {
      "q": inputData,
      "page": pagetest.selected
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
  }, [inputData, pagetest])



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
        {/* <Pagination page={page} setPage={setPage} pageInfo={pageInfo}/> */}
        <ReactPaginate
          pageCount={pageInfo && pageInfo.totalPages}
          pageRangeDisplayed={5}
          marginPagesDisplayed={0}
          breakLabel={""}
          previousLabel={"Prev"}
          nextLabel={"Next"}
          onPageChange={handlePageChange}
          containerClassName={"pagination-ul"}
          pageClassName={'pageButton'}
          activeClassName={"currentPage"}
          previousClassName={"switchPage"}
          nextClassName={"switchPage"}
        />
      </div>
      <div className='questionPageAside'>
        <Aside />
      </div>
    </div>
    
  )
}
