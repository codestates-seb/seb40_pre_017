import { useEffect, useState } from 'react';
import '../../css/questionPage/Pagination.scss'

export default function Pagination({ page, setPage, pageInfo }) {
  //totalpage 필터와 검색 될때까지 임시로 100페이지 생성
  const [maxPage, setMaxPage] = useState(1);
  const [currentPage, setCurrentPage] = useState(0);
  const [arr, setArr] = useState([1])

  // let maxPage = pageInfo.totalPages;

  let totlaPages = Array.from({length: maxPage}, (_,idx)=> idx + 1);
  let pageArr = Array(Math.ceil(maxPage / 5)).fill().map(() => totlaPages.splice(0, 5));
  
  useEffect(() => {
    setMaxPage(pageInfo.totalPages);
    setArr(pageArr[currentPage]);
    
    console.log(pageInfo.totalPages)
  },[currentPage, pageInfo])
  
  return (
    <div className='pagenation'>
      {maxPage > 5 && currentPage > 0 ? 
        <button className='switchPage' onClick={()=> {setCurrentPage(currentPage - 1)}}>Prev</button> 
      : null}
      <div className='pageBtns'>
      {arr.map(el => (
        <button 
          onClick={() => {
            setPage(el);
          }}
          className='pageBtn'
          aria-current={page === el ? 'page' : undefined}
        >{el}</button>
      ))}
      </div>
      {maxPage > 5 && currentPage < pageArr.length ? 
        <button className='switchPage' onClick={()=> {setCurrentPage(currentPage + 1)}}>Next</button> 
      : null}

    </div>
  )
}
