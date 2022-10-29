import React from 'react'
import '../../css/addContent/AddContent.scss'

export default function AddContent({content, setContent}) {


  const inputContent = (e) => {
    e.preventDefault();
    setContent(e.target.value);
  }

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
