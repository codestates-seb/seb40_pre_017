import React from 'react'
import '../../css/addContent/AddContent.scss'

export default function AddContent({content, setContent, appearNext, contentInput, setNextContentDis}) {


  const inputContent = (e) => {
    e.preventDefault();
    setContent(e.target.value);

    if(e.target.value.length >= 20){
      setNextContentDis(false);
    }else{
      setNextContentDis(true);
    }
  }

  return (
    <div>
        <input 
          className='AddInput' 
          name='content'
          type='text' 
          onFocus={appearNext}
          ref={contentInput}
          onChange={inputContent} 
          value={content}
          disabled
        ></input>
    </div>
  )
}
