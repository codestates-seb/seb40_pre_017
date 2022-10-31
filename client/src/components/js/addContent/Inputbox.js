import React from 'react'
import AddContent from './AddContent'

export default function Inputbox({setTitle, setContent, tags, setTags, title, content}) {
  //유효성검사 해아함


  // title 입력
  const inputTitle = (e) => {
    e.preventDefault();
    setTitle(e.target.value);
  }

  // tag 입력
  const inputTag = (event) => {
    const filtered = tags.filter((el) => el === event.target.value);
    if(filtered.length > 0){
      event.target.value = '';
    }
    else if (event.target.value !== undefined) {
      setTags([...tags ,{name: event.target.value}])
      event.target.value = '';
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
          type='text' 
          onChange={inputTitle} 
          value={title}
        ></input>
      </div>

      <div className='inputbox'>
        <h3>What are the details of your problem?</h3>
        <p>Introduce the problem and expand on what you put in the title. Minimum 20 characters.</p>
        <AddContent 
          content={content}
          setContent={setContent}
          />
      </div>

      <div className='inputbox'>
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
            type='text' 
            onKeyUp={(event) => (event.key === 'Enter' ? inputTag(event) : null)}
            placeholder='Press enter to add tags'
          ></input>
        </div>
          
      </div>
    </div>
  )
}
