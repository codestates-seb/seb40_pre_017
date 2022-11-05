import React, { useRef, useState } from 'react'
import AddContent from './AddContent'
import '../../css/addContent/Inputbox.scss'

export default function Inputbox({setTitle, tags, setTags, title, content, setSubmitDis, contentInput, type}) {
  //유효성검사 해아함


  // title 입력
  const inputTitle = (e) => {
    e.preventDefault();
    setTitle(e.target.value);

    //next 버튼 비활성화 & 활성화
    if(e.target.value.length >= 15){
      setNextTitleDis(false);
    }
    else{
      setNextTitleDis(true);
    }
  }

  const [nextContentDis, setNextContentDis] = useState(true);


  // tag 입력
  const inputTag = (event) => {
    const filtered = tags.filter((el) => el === event.target.value);
    if(filtered.length > 0){
      event.target.value = '';
      setNextTagDis(true);
    }
    else if (event.target.value !== undefined) {
      setTags([...tags ,{name: event.target.value}])
      event.target.value = '';
      setNextTagDis(false);
    }
  };

  // tag 삭제
  const removeTags = (indexToRemove) => {
    setTags(tags.filter((_, index) => index !== indexToRemove));
  };

  //next버튼
  const [ titleNext, setTitleNext ] = useState(false);
  const [contentNext, setContentNext] = useState(false);
  const [tagNext, setTagNext] =useState(false);

  //next 버튼 onfocus일때 나타나기
  const [ nextTitleDis, setNextTitleDis ] = useState(true);
  const [ nextTagDis, setNextTagDis ] =useState(true);
  const appearNext = (e) => {
    if(e.target.name === 'title'){
      if(e.target.value.length < 5)setTitleNext(true);
    }
    
    else if(e.target.name === 'tag'){
      if(tags.length < 1) setTagNext(true);
      setTagBorder(true);
    }
    else{
      if(contentInput.current.getInstance().getMarkdown().length < 30) setContentNext(true);
    }
  }
  const appearContentNext = () => {
    if(contentInput.current.getInstance().getMarkdown().length < 30) setContentNext(true);
  }

  //next 버튼을 눌렀을때 다음 창 비활성화 풀리기
  const contentBox = useRef();
  const tagbox = useRef();
  const tagInput = useRef();
  const clickNext = (e) => {
    if(e.target.name === 'title'){
      contentBox.current.classList.remove('disable');
      contentInput.current.disabled = false;
    }
    else if(e.target.name === 'content'){
      tagbox.current.classList.remove('disable');
      tagInput.current.disabled = false;
    }
    else if(e.target.name === 'tag'){
      setSubmitDis(false);
    }
  }

  const [tagBorder, setTagBorder] = useState(false);
  const tagBlur = () => {
    setTagBorder(false);
  }


  // aside
  // onfocus일때 나타난다
  // false 면 사라진다

  return (
    <div>
      <div className='inputbox'>
        <h3>Title</h3>
        <p>Be specific and imagine you’re asking a question to another person.</p>
        <input 
          className='addInput1' 
          name='title'
          type='text' 
          onFocus={appearNext}
          onChange={inputTitle} 
          value={title}
        ></input>
        { titleNext ? 
          <button 
            className='blueBtn'
            name='title' 
            onClick={clickNext}
            disabled={ nextTitleDis ? true : false }
          >Next</button> : null }
      </div>

      <div className={content&& content.length >= 30 ? 'inputbox' :'inputbox disable'} ref={contentBox}>
        <h3>What are the details of your problem?</h3>
        <p>Introduce the problem and expand on what you put in the title. Minimum 20 characters.</p>
        <AddContent 
          content={content}
          appearNext={appearContentNext}
          contentInput={contentInput}
          setNextContentDis={setNextContentDis}
        />
        { contentNext ? 
          <button 
            name='content'
            className='blueBtn'
            onClick={clickNext}
            disabled={ nextContentDis ? true : false}
          >Next</button> : null }
      </div>

      <div className={tags && tags.length >= 1 ? 'inputbox':'inputbox disable'} ref={tagbox}>
        <h3>Tags</h3>
        <p>Add up to 5 tags to describe what your question is about. Start typing to see suggestions.</p>
        <div className={tagBorder ? 'tagFocus tagBox' : 'tagBox'}>
          <ul id='tags'>
            {tags.map((tag, index) => (
              <li key={index} className='tag'>
                <span className='tag-title'>{tag.name}</span>
                <span className='tag-close-icon' 
                onClick={() => removeTags(index)}>
                  &times;
                </span>
              </li>
            ))}
          </ul>
          <input 
            className='tagTextInput' 
            name='tag'
            type='text' 
            onFocus={appearNext}
            onBlur={tagBlur}
            ref={tagInput}
            onKeyUp={(event) => (event.key === 'Enter' ? inputTag(event) : null)}
            placeholder='Press enter to add tags'
            disabled={tags && tags.length >= 1 ? false:true}
          ></input>
        </div>
        { tagNext ? 
          <button 
            name='tag' 
            className='blueBtn' 
            onClick={clickNext} 
            disabled={nextTagDis ? true : false}
          >Next</button> : null }
      </div>
    </div>
  )
}
