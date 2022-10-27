import React from 'react'
import '../../css/vote/Vote.scss'
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTriangle } from "@fortawesome/free-brands-svg-icons";

export default function Vote({item}) {
  return (
    <div className='voteWrap'>
{/* <button><i class="fa-solid fa-triangle"></i></button>  */}
<i class="fa-duotone fa-triangle"></i>
<div>{item.question.votes}</div>
      <button>down</button>
      <div className='etcBtn'>
        <button>bookmark</button>
        <button>active</button>
      </div>
    </div>
  )
}
