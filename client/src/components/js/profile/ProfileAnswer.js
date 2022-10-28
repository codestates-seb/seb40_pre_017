import React from 'react'
import '../../css/profile/ProfileAnswer.scss'

export default function ProfileAnswer({item}) {
  //createdAt 시간 계산

  return (
    <div className='profileWrapA'>
      <p>answered createdAt ago</p>
      <div className='profileName'>
        <img src={item.profileImage} alt='profileImg'/>
        <p className='username'>{item.userName}</p>
      </div>
      
    </div>
  )
}
