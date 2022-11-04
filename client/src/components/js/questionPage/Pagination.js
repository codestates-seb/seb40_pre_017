import '../../css/questionPage/Pagination.scss'

export default function Pagination({ page, setPage, pageInfo }) {
  let btn = Array.from({length:pageInfo.totalPages}, (_,idx)=> idx + 1);

  return (
    <div className='pagenation'>
      {btn.map(el => (
        <button 
          onClick={() => {
            setPage(el);
          }}
          className='pageBtn'
          aria-current={page === el ? 'page' : undefined}
        >{el}</button>
      ))}
    </div>
  )
}
