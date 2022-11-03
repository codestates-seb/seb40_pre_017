import React from 'react'
import '../../css/addContent/AddContent.scss'
// import MarkDown from './MarkDown';

import '@toast-ui/editor/dist/toastui-editor.css';
import { Editor } from '@toast-ui/react-editor';

export default function AddContent({content, setContent, appearNext, contentInput, setNextContentDis, type}) {


  const inputContent = () => {
    if(type !== 'answer'){
      if(contentInput.current.getInstance().getMarkdown().length >= 20){
        setNextContentDis(false);
      }else{
        setNextContentDis(true);
      }
    }
  }

  const handledisabled = () => {
    if(type === 'answer') return false
    else if(contentInput.current.getInstance().getMarkdown() && contentInput.current.getInstance().getMarkdown().length > 20) return false
    return true
  }

  return (
    <div>
      {/* <MarkDown/> */}
      <div className='AddInput'>
        <Editor
          initialValue={content}
          previewStyle="tab"
          min-height="250px"
          initialEditType="markdown"
          useCommandShortcut={true}
          toolbarItems={
            [['heading', 'bold', 'italic', 'code'],
            ['link', 'quote','codeblock','image','table'],
            [],
            [],
            ['ul', 'ol','task','hr']]
          }
          onChange={inputContent}
          onFocus={appearNext}
          ref={contentInput}
          disabled={handledisabled}
        />
      </div>
        {/* <input 
          className='AddInput' 
          name='content'
          type='text' 
          onFocus={appearNext}
          ref={contentInput}
          onChange={inputContent} 
          value={content}
          disabled={handledisabled}
        ></input> */}
    </div>
  )
}
