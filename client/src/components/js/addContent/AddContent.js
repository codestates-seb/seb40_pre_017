import React from 'react'
import '../../css/addContent/AddContent.scss'

export default function AddContent({content, setContent, appearNext, contentInput, setNextContentDis, type}) {


  const inputContent = (e) => {
    e.preventDefault();
    setContent(e.target.value);

    if(e.target.value.length >= 20){
      setNextContentDis(false);
    }else{
      setNextContentDis(true);
    }
  }

  const handledisabled = () => {
    if(type === 'answer') return false
    else if(content && content.length > 20) return false
    return true
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
          // disabled={content && content.length > 20 ? false : true}
          disabled={handledisabled}
        ></input>
    </div>
  )
}
