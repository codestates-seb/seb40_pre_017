import React from 'react';
import '../../css/aside/aside.scss';
import Memo from './Memo';

export default function EditAside() {

  let data = [
    [
      "How to Edit", 
      [
        "Correct minor typos or mistakes",
        "Clarify meaning without changing it",
        "Add related resources or links",
        "Always respect the author’s intent",
        "Don’t use edits to reply to the author"
      ]
    ],
    [
      "How to Format", 
      [
        "create code fences with backticks or tildes",
        "put retumns between paragraphs",
        "quote by placing > at start of line",
        `to make links (use https whenever possible)
        <https://example/. com>
        [example](https://example/. com)
        <a href= 'https://example/. com" > example</a>`
      ]
    ]
  ]

  return (
    <div className='AsideInner'>
      <div className='memoBox'>
        <Memo data={data[0]} />
      </div>
      <div className='memoBox'>
        <Memo data={data[1]} />
      </div>
    </div>
  )
}
