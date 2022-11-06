import '../../css/questionPage/Pagination.scss'

// export default function Pagination({ page, setPage, pageInfo }) {
  export default function Pagination({setPage, pageInfo }) {

  // let btn = Array.from({length: Math.floor(pageInfo.totalElements)}, (_,idx)=> idx + 1);
  let maxPage = Math.floor(100)
  let btn = Array.from({length: maxPage}, (_,idx)=> idx + 1);
  let page = 1;
  // const fivePage = () => {
  //   let pageNum = page;
  //   for(let i =1; i<=5; i++){
  //     if(page < 5){
  //       return (<button 
  //         onClick={() => {
  //           setPage(i);
  //         }}
  //         className='pageBtn'
  //         aria-current={page === i ? 'page' : undefined}
  //       >{i}</button>)
  //     }
  //   }
  // }

  const test = () => {
    return <button>11</button>
  }


  return (
    <div className='pagenation'>
      {/* {btn.map(el => (
        <button 
          onClick={() => {
            setPage(el);
          }}
          className='pageBtn'
          aria-current={page === el ? 'page' : undefined}
        >{el}</button>
      ))} */}
    </div>
  )
}
