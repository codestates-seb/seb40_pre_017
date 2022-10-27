import React from 'react'

export default function Vote({item}) {
  return (
    <div className='voteWrap'>
      <button>up</button>
      <div>{item.question.votes}</div>
      <button>down</button>
      <div className='etcBtn'>
        <button>bookmark</button>
        <button>active</button>
      </div>
    </div>
  )
}
