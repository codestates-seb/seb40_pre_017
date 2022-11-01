import React from 'react'
import '../../css/aside/memo.scss'


export default function Memo({data}) {
  return (
    <div className='memoInner'>
      <div className='memoTitle'>{data[0]}</div>
      <ul>
        {data[1].map((el, index) => {
          return <li key={index}>{el}</li>
        })}
      </ul>
    </div>
  )
}
