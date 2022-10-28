import React from 'react'
import AddContent from './AddContent'

export default function Inputbox() {
  return (
    <div>
        <div className='inputbox'>
            <h3>Title</h3>
            <p>Be specific and imagine you’re asking a question to another person.</p>
            <input className='addInput1' type='text'></input>
          </div>
          <div className='inputbox'>
            <h3>What are the details of your problem?</h3>
            <p>Introduce the problem and expand on what you put in the title. Minimum 20 characters.</p>
            <AddContent />
          </div>
          <div className='inputbox'>
            <h3>What did you try and what were you expecting?</h3>
            <p>Describe what you tried, what you expected to happen, and what actually resulted. Minimum 20 characters.</p>
            <AddContent />
          </div>
          <div className='inputbox'>
            <h3>Tags</h3>
            <p>Add up to 5 tags to describe what your question is about. Start typing to see suggestions.</p>
            <input className='addInput1' type='text'></input>
          </div>
    </div>
  )
}
