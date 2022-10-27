import React from 'react'

export default function Profile({item}) {
  //createdAt 시간 계산

  return (
    <div className='profileWrap'>
      <p>asked createdAt ago</p>
      <img src={item.member.profileImage} alt='profileImg'/>
      <p className='username'>{item.member.userName}</p>
    </div>
  )
}
