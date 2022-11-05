import React from 'react'
import '../../css/addContent/AddContent.scss'
// import MarkDown from './MarkDown';

import '@toast-ui/editor/dist/toastui-editor.css';
import { Editor } from '@toast-ui/react-editor';
import axios from 'axios';

export default function AddContent({content, appearNext, contentInput, setNextContentDis, type}) {


  const inputContent = () => {
    if(type !== 'answer'){
      if(contentInput.current.getInstance().getMarkdown().length >= 30){
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
          toolbarItems={[
            ['bold', 'italic'],
            ['link','quote', 'code', 'image', 'codeblock'],
            [],
            [],
            // ['indent', 'outdent','task','table', 'ul','ol' ],
          ]}
          onChange={inputContent}
          onFocus={appearNext}
          ref={contentInput}
          disabled={handledisabled}
          hooks={{
            addImageBlobHook: async (blob, callback) => {
                console.log(blob);
                const imgUrl = await axios.post('/api')
                callback('/api', '이미지');
              }
            }}
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
