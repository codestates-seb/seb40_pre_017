import React from 'react'
import '../../../css/user/common/googleBtn.scss';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faGoogle, faGithub, faFacebook } from "@fortawesome/free-brands-svg-icons";


export default function Googlebtn({content}) {
  return (
    <button className='googlebtn'>
      <FontAwesomeIcon className='btnIcon' icon={faGoogle} />
      {content}
    </button>
  )
}
