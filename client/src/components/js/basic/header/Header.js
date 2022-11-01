import React from 'react';
import '../../../css/basic/header/header.scss'
import Nav from './Nav';


export default function Header({changeInputData, islogined, memberData, logoutControll}) {
  return (
    <header className='header'>
      <Nav changeInputData={changeInputData} islogined={islogined} memberData={memberData} logoutControll={logoutControll}  />
    </header>
  )
}
