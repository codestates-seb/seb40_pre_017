import React, {useState, useEffect} from 'react'
import '../../../css/basic/header/user.scss'
import Userinfo from './Userinfo'
import { getItemWithExpireTime }  from '../../../../util/controlStorage'

export default function User({memberData, logoutControll}) {

  const [toggleOpen, setToggleOpen] = useState(false);

  const toggleOpenChange = () => {
    setToggleOpen(!toggleOpen)
  }

  return (
    <>
      <div onClick={toggleOpenChange} className={'userArea ' + (toggleOpen ? "active" : "")}>
        <img className='userIcon' src="https://www.gravatar.com/avatar/b755eca3f0896d3d0751a1bb7fb5e06d?s=48&d=identicon&r=PG" alt="userIcon" />
        {/* <img className='userIcon' src={userInfo.img} alt="userIcon" /> */}
        <div className='userNumber'>1</div>
        {/* <div className='userNumber'>{userInfo.number}</div> */}
        {toggleOpen && <div className='UserinfoArea'>
          <Userinfo memberData={memberData} logoutControll={logoutControll} />
        </div>}
      </div>

    </>
    
  )
}
