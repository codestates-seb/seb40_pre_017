import React from 'react'
import '../../css/profile/profile.scss'
import createdAt from '../createdAt/CreatedAt'

export default function Profile({item, time}) {
  //createdAt 시간 계산

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
