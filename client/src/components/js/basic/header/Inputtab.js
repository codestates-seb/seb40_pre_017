import React from 'react'
import '../../../css/basic/header/inputtab.scss'
import { Link } from 'react-router-dom';
import Input from './Input';

let flexItem = [
  [
    ["[tag]", "search within a tag"],
    ["user:1234", "search by author"],
    [`"words here"`, "exact phrase"],
    ["collective:'Name'", "collective content"]
  ],
  [
    ["answers:0", "unanswered questions"],
    ["score:3", "posts with a 3+ score"],
    ["is:question", "type of post"],
    ["isaccepted:yes", "search within status"]
  ]
]

export default function Inputtab({isFocused ,focusChange}) {
  return (
    <>    
      {/* <Input name="검색2" focusChange={focusChange} /> */}
      <div className='inputTab'>
        <div></div>
        <div className='flexItem'>
          {flexItem[0].map(function(el, index) {
            return <div key={index} className='item'>
              <span className='inputTabFont color-black'>{el[0]}</span>
              <span className='inputTabFont color-2'>{el[1]}</span>
            </div>
          })}
        </div>
        <div className='flexItem'>
          {flexItem[1].map(function(el, index) {
            return <div key={index} className='item'>
              <span className='inputTabFont color-black'>{el[0]}</span>
              <span className='inputTabFont color-2'>{el[1]}</span>
            </div>
          })}
        </div>
      </div>
      {/* <div className='inputTabBottom'>
        <button>Ask a question</button>
        <Link to={'#'}>Search help</Link>
      </div> */}
      </>
  )
}
