import React from 'react';
import '../../../css/basic/header/nav.scss';
import Hamburger from './Hamburger';
import Logo from './Logo';
import Snav from './Snav';
import Navinput from './Navinput';
import Navbtn from './Navbtn';
import User from './User';

export default function Nav({changeInputData, islogined, memberData, logoutControll}) {


  // 데이터나 로그인, 엑세스 토큰이 null 일 경우 리프레시 토큰이 있는지 확인하고 있으면 재 발급 요청.
  // 리프레시 토큰이 없으면? 아무것도 안함.

  return (
    <>
      <nav className='nav'>
        <Hamburger />
        <Logo />
        <Snav />
        <Navinput changeInputData={changeInputData} />
        {islogined && memberData ? <User memberData={memberData} logoutControll={logoutControll} /> : <Navbtn />}
      </nav>
    </>
  )
}
