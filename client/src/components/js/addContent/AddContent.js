import React from 'react'
import '../../css/addContent/AddContent.scss'

import '@toast-ui/editor/dist/toastui-editor.css';
import { Editor } from '@toast-ui/react-editor';
import axios from 'axios';

axios.defaults.withCredentials = true;

export default function AddContent({content, appearNext, contentInput, setNextContentDis, type, setContentGuide}) {

  const REACT_APP_API_URL = process.env.REACT_APP_API_URL;

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
          ]}
          onChange={inputContent}
          onFocus={appearNext}
          onBlur={() => setContentGuide(false)}
          ref={contentInput}
          disabled={handledisabled}
          hooks={{
            addImageBlobHook: async (blob, callback) => {
              (async () => {
                  const formData = new FormData();
                  formData.append("img", blob);

                  const res = await axios.post(`${REACT_APP_API_URL}questions/uploadImage`, formData);

                  callback(res.data, "input alt text");
                })();
      
              return false;
          }
          }}
        />
      </div>
    </div>
  )
}
