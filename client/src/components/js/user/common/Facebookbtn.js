import React from 'react'
import '../../../css/user/common/facebookBtn.scss';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faGoogle, faGithub, faFacebook } from "@fortawesome/free-brands-svg-icons";


export default function Facebookbtn({content}) {
  return (
    <button className='facebookBtn'>
      <FontAwesomeIcon className='btnIcon' icon={faFacebook} />
      {content}
    </button>
  )
}
