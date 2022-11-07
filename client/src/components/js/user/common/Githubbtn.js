import React from 'react'
import '../../../css/user/common/githubBtn.scss';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faGithub } from "@fortawesome/free-brands-svg-icons";


export default function Githubbtn({content}) {
  return (
    <button className='githubBtn'>
      <FontAwesomeIcon className='btnIcon' icon={faGithub} />
      {content}
    </button>
  )
}
