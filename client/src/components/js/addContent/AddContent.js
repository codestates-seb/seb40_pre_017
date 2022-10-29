import React from 'react'
import '../../css/addContent/AddContent.scss'

export default function AddContent({inputContent, content}) {
  return (
    <div>
        <input 
          className='AddInput' 
          type='text' 
          onChange={inputContent} 
          value={content}
        ></input>
    </div>
  )
}
