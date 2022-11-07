import React, {useState} from 'react'
import '../../../css/basic/header/user.scss'
import Userinfo from './Userinfo'

export default function User({memberData, logoutControll}) {

  const [toggleOpen, setToggleOpen] = useState(false);
  const toggleOpenChange = () => {
    setToggleOpen(!toggleOpen)
  }
  return (
    <>
      <div onClick={toggleOpenChange} className={'userArea ' + (toggleOpen ? "active" : "")}>
        <img className='userIcon' src={memberData.imageUrl} alt="userIcon" />
        <div className='userNumber'>1</div>
        {toggleOpen && <div className='UserinfoArea'>
          <Userinfo memberData={memberData} logoutControll={logoutControll} />
        </div>}
      </div>

    </>
    
  )
}
