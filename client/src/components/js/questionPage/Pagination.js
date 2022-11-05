import { useEffect, useState } from 'react';
import '../../css/questionPage/Pagination.scss'

// export default function Pagination({ page, setPage, pageInfo }) {
  export default function Pagination({setPage, pageInfo }) {

  // let btn = Array.from({length: Math.floor(pageInfo.totalElements/5)}, (_,idx)=> idx + 1);
  let maxPage = pageInfo.totalElements
  let totlaPages = Array.from({length: maxPage}, (_,idx)=> idx + 1);
  let page = 1;
  let pageArr = Array(Math.ceil(maxPage / 5)).fill().map(() => totlaPages.splice(0, 5));
  let currentPages = pageArr[0];
  
  const [currentPage, setCurrentPage] = useState(0);
  useEffect(() => {
    // console.log(arr)
    currentPages = pageArr[currentPage];
  },[currentPage])
  
  return (
    <div className='pagenation'>
      {maxPage > 5 && currentPage > 0 ? 
        <button onClick={()=> {setCurrentPage(currentPage - 1)}}>Prev</button> 
      : null}
      {currentPages.map(el => (
        <button 
          onClick={() => {
            setPage(el);
          }}
          className='pageBtn'
          aria-current={page === el ? 'page' : undefined}
        >{el}</button>
      ))}
      {maxPage > 5 && currentPage < pageArr.length ? 
        <button onClick={()=> {setCurrentPage(currentPage + 1)}}>Next</button> 
      : null}

    </div>
  )
}
