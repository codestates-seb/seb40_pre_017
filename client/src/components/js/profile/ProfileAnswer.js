import React from 'react'
import '../../css/profile/ProfileAnswer.scss'
import createdAt from '../createdAt/CreatedAt'

export default function ProfileAnswer({item, time}) {
  //createdAt 시간 계산

  return (
    <div className='profileWrapA'>
      <p>{createdAt(time)}</p>
      <div className='profileName'>
        <img src={item.profileImage} alt='profileImg'/>
        <p className='username'>{item.userName}</p>
      </div>
      
    </div>
  )
}
