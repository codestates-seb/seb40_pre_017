import React from 'react'
import '../../css/vote/Vote.scss'

export default function Vote({item}) {
  return (
    <div className='voteWrap'>
      <button className='voteUp'></button> 
      <div className='voteCount'>{item}</div>
      <button className='voteDown'></button>
      <div className='etcBtn'>
        <i className="fa-regular fa-bookmark"></i>
        <i className="fa-solid fa-clock-rotate-left"></i>
      </div>
    </div>
  )
}
