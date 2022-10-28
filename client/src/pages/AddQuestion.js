import React from 'react'
import Background from '../assets/imgs/Background.svg'
import './AddQuestion.scss'
import Inputbox from '../components/js/addContent/Inputbox'

export default function AddQuestion() {
  return (
    <div className='addOut'>
      <div className='addQuestion'>
        <div className='addHeadWrap'>
          <img src={Background} alt='backgroundImg'/>
          <h1>Ask a public question</h1>
        </div>
        
        <div className='addContent'>
          <div className='addGuide'>
            Writing a good question
            You’re ready to ask a programming-related question and this form will help guide you through the process.

            Looking to ask a non-programming question? See the topics here to find a relevant site.

            Steps
            Summarize your problem in a one-line title.
            Describe your problem in more detail.
            Describe what you tried and what you expected to happen.
            Add “tags” which help surface your question to members of the community.
            Review your question and post it to the site.
          </div>
          <Inputbox />
          <button>Review your question</button>
        </div>
        
      </div>
    </div>
  )
}
