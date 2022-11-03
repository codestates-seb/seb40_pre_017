import React from 'react'
import '../../css/tags/Tags.scss'

export default function Tags({item}) {
  return (
    <div className='tags'>
        {
        item.tags && item.tags.map((tag, idx) => (
            <div className='tag' key={idx}>
            {tag}
            </div>
        ))
        }
    </div>
  )
}
