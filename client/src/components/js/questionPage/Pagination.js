import React from 'react'
import '../../css/questionPage/Pagination.scss'

export default function Pagination({ page, setPage, pageInfo }) {
  let btn = Array.from({length:pageInfo.totalPages}, (_,idx)=> idx + 1);
  const handleClick = (e) => {
    setPage(e.target.name);
  }

  return (
    <div className='pagenation'>
      {btn.map(el => (
        <button 
          name={el} 
          onClick={handleClick}
          className={page === el ? 'click pageBtn' : 'pageBtn'}
        >{el}</button>
      ))}
    </div>
  )
}
