import React from 'react'
import '../../../css/basic/header/userinfo.scss'
// 리액트에서 사용하기 위해 추가
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
// 솔리드 아이콘
import { faInbox, faTrophy, faCircleQuestion } from "@fortawesome/free-solid-svg-icons";
import { faStackExchange } from "@fortawesome/free-brands-svg-icons";

export default function Userinfo({ memberData, logoutControll }) {

  return (
    <div className='userInfo'>
      <img className='userInfoIcon' src={memberData.imageUrl} alt="userInfoIcon" />
      <div className='userName'>{memberData.username}</div>
      <div className='userEmail'>{memberData.email}</div>

      <button onClick={logoutControll} className='logoutBtn'>Logout</button>

      <ul className='userInfoLink'>
        <li>
          <FontAwesomeIcon className='userInfoLinkItem' icon={faInbox} />
        </li>
        <li>
        <FontAwesomeIcon className='userInfoLinkItem' icon={faTrophy} />
        </li>
        <li>
        <FontAwesomeIcon className='userInfoLinkItem' icon={faStackExchange} />
        </li>
        <li>
        <FontAwesomeIcon className='userInfoLinkItem' icon={faCircleQuestion} />
        </li>
      </ul>
    </div>
  )
}
