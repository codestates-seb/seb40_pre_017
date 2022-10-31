import React, { useRef, useState } from 'react'
import AddContent from './AddContent'
import '../../css/addContent/Inputbox.scss'

export default function Inputbox({setTitle, setContent, tags, setTags, title, content, setSubmitDis}) {
  //유효성검사 해아함


  // title 입력
  const inputTitle = (e) => {
    e.preventDefault();
    setTitle(e.target.value);

    //next 버튼 비활성화 & 활성화
    if(e.target.value.length >= 5){
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
    console.log(indexToRemove)
  };

  //next버튼
  // onfocus 일때 나타난다
  // 글자수 적으면 비활성화
  // 글자수 이상되면 활성화
  // onfocus, 글자수확인, 버튼눌럿을때 사라진다
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
    else if(e.target.name === 'content'){
      if(e.target.value.length < 20) setContentNext(true);
    }
    else if(e.target.name === 'tag'){
      if(e.target.value.length < 1) setTagNext(true);
    }
  }

  //next 버튼을 눌렀을때 다음 창 비활성화 풀리기
  const contentBox = useRef();
  const contentInput = useRef();
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
      console.log(1)
    }
    else if(e.target.name === 'tag'){
      setSubmitDis(false);
    }
  }


  // aside
  // onfocus일때 나타난다
  // false 면 사라진다

  //inputbox
  //content || tags가 있으면 활성화
  //비활성화시 위쪽 next 버튼눌러야 활성화

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
            name='title' 
            onClick={clickNext}
            disabled={ nextTitleDis ? true : false }
          >Next</button> : null }
      </div>

      <div className='inputbox disable' ref={contentBox}>
        <h3>What are the details of your problem?</h3>
        <p>Introduce the problem and expand on what you put in the title. Minimum 20 characters.</p>
        <AddContent 
          content={content}
          setContent={setContent}
          appearNext={appearNext}
          contentInput={contentInput}
          setNextContentDis={setNextContentDis}
        />
        { contentNext ? 
          <button 
            name='content'
            onClick={clickNext}
            disabled={ nextContentDis ? true : false}
          >Next</button> : null }
      </div>

      <div className='inputbox disable' ref={tagbox}>
        <h3>Tags</h3>
        <p>Add up to 5 tags to describe what your question is about. Start typing to see suggestions.</p>
        <div className='tagBox'>
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
            ref={tagInput}
            onKeyUp={(event) => (event.key === 'Enter' ? inputTag(event) : null)}
            placeholder='Press enter to add tags'
            disabled
          ></input>
        </div>
        { tagNext ? <button name='tag' onClick={clickNext} disabled={nextTagDis ? true : false}>Next</button> : null }
      </div>
    </div>
  )
}
