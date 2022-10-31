import React from 'react';
import '../../css/aside/aside.scss';
import Memo from './Memo';

export default function Aside() {

  let data = [
    [
      "The Overflow Blog", 
      [
        "ideal fit for developing blockchains",
        "Environments on-demand (Ep. 479)",
        "Add related resources or links",
        "Always respect the author’s intent",
        "Don’t use edits to reply to the author"
      ]
    ],
    [
      "Featured on Meta", 
      [
        "The 2022 Community-a-thon has begun!",
        "Mobile app infrastructure being decommissioned",
        "The Ask Wizard (2022) has graduated",
        "Staging Ground Workflow: Canned Comments"
      ]
    ],[
      "Hot Meta Posts", 
      [
        "Any plans to burninate [project-planning]?",
        "Should I drastically edit the question or create a new community wiki one?"
      ]
    ]
  ]

  return (
    <div className='AsideInner'>
      <div className='memoBox'>
        <Memo data={data[0]} />
        <Memo data={data[1]} />
      </div>
      <div className='memoBox'>
        <Memo data={data[2]} />
      </div>
    </div>
  )
}
