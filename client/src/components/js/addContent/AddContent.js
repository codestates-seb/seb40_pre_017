import React from 'react'
import '../../css/addContent/AddContent.scss'
// import MarkDown from './MarkDown';

import '@toast-ui/editor/dist/toastui-editor.css';
import { Editor } from '@toast-ui/react-editor';
import axios from 'axios';

axios.defaults.withCredentials = true;

export default function AddContent({content, appearNext, contentInput, setNextContentDis, type, setContentGuide, accessToken}) {

  const REACT_APP_API_URL = process.env.REACT_APP_API_URL;

  // axios.defaults.headers.common["Authorization"] = window.sessionStorage.getItem("jwtToken");
  // axios.defaults.withCredentials = true;

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
          onBlur={() => setContentGuide(false)}
          ref={contentInput}
          disabled={handledisabled}
          // hooks={{
          //   addImageBlobHook:  async (blob, callback) => {
          //       const formData = new FormData();
          //       formData.append('img', blob);
          //       console.log(blob)
          //       const imgUrl = await axios.post(`${REACT_APP_API_URL}questions/uploadImage`, {
          //       headers: {
          //         'Content-Type': 'multipart/form-data',
          //       },
          //       body: formData
          //       })
          //       .then((res) => {
          //         console.log(res)
          //       })
          //       .catch(error => {
          //         console.log(error.response);
          //       });
          //       callback(imgUrl);
          //       return false;
          //     }
          //   }}

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
        

          // hooks={{
          //   addImageBlobHook:  (blob, callback) => {
          //       console.log(blob)
          //       callback('imgUrl');
          //     }
          //   }}
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
