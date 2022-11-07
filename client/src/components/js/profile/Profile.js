import React from 'react'
import '../../css/profile/profile.scss'
import createdAt from '../createdAt/CreatedAt'

export default function Profile({item, time}) {

  return (
    <div className='profileWrap'>
      <p>{createdAt(time)}</p>
      <div className='profileName'>
        <img src={item.profileImage} alt='profileImg'/>
        <p className='username'>{item.username}</p>
      </div>
      
    </div>
  )
}
