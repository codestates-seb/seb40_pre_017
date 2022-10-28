import React from 'react'
import AddContent from './AddContent'

export default function Inputbox({setTitle, setContent, tags, setTags}) {

  const inputTitle = (e) => {
    e.preventDefault();
    setTitle(e.target.value);
  }

  const inputContent = (e) => {
    e.preventDefault();
    setContent(e.target.value);
  }

  const inputTag = (event) => {
    const filtered = tags.filter((el) => el === event.target.value);
    if(filtered.length > 0){
      event.target.value = '';
    }
    else if (event.target.value !== undefined) {
      // setInputTag(event.target.value)
      setTags([...tags ,event.target.value])
      event.target.value = '';
    }
  };

  const removeTags = (indexToRemove) => {
    setTags(tags.filter((_, index) => index !== indexToRemove));
    console.log(indexToRemove)
  };

  return (
    <div>
      <div className='inputbox'>
        <h3>Title</h3>
        <p>Be specific and imagine you’re asking a question to another person.</p>
        <input className='addInput1' type='text' onChange={inputTitle}></input>
      </div>

      <div className='inputbox'>
        <h3>What are the details of your problem?</h3>
        <p>Introduce the problem and expand on what you put in the title. Minimum 20 characters.</p>
        <AddContent inputContent={inputContent}/>
      </div>

      {/* <div className='inputbox'>
        <h3>What did you try and what were you expecting?</h3>
        <p>Describe what you tried, what you expected to happen, and what actually resulted. Minimum 20 characters.</p>
        <AddContent />
      </div> */}

      <div className='inputbox'>
        <h3>Tags</h3>
        <p>Add up to 5 tags to describe what your question is about. Start typing to see suggestions.</p>
        <div className='tagBox'>
        <ul id='tags'>
          {tags.map((tag, index) => (
            <li key={index} className='tag'>
              <span className='tag-title'>{tag}</span>
              <span className='tag-close-icon' 
              onClick={() => removeTags(index)}>
                &times;
              </span>
            </li>
          ))}
        </ul>
          <input className='tagTextInput' type='text' 
          onKeyUp={(event) => (event.key === 'Enter' ? inputTag(event) : null)}
          placeholder='Press enter to add tags'
          ></input>
        </div>
          
      </div>
    </div>
  )
}
