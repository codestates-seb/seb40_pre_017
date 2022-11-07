import React from 'react'
import '../../../css/user/common/googleBtn.scss';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faGoogle } from "@fortawesome/free-brands-svg-icons";


export default function Googlebtn({content}) {
  return (
    <button className='googlebtn'>
      <FontAwesomeIcon className='btnIcon' icon={faGoogle} />
      {content}
    </button>
  )
}
