import React from 'react'
import '../../../css/basic/header/userinfo.scss'
// 리액트에서 사용하기 위해 추가
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
// 솔리드 아이콘
import { faInbox, faTrophy, faCircleQuestion } from "@fortawesome/free-solid-svg-icons";
import { faStackExchange } from "@fortawesome/free-brands-svg-icons";

export default function Userinfo({Userinfo}) {
  return (
    <div className='userInfo'>
      <img className='userInfoIcon' src="https://www.gravatar.com/avatar/b755eca3f0896d3d0751a1bb7fb5e06d?s=48&d=identicon&r=PG" alt="userInfoIcon" />
      {/* <img className='userInfoIcon' src={Userinfo.img} alt="userInfoIcon" /> */}

      <div className='userName'>username</div>
      {/* <div className='userName'>{Userinfo.name}</div> */}

      <div className='userEmail'>qwer1234@gmail.com</div>
      {/* <div className='userEmail'>{Userinfo.email</div> */}

      <button className='logoutBtn'>Logout</button>
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
