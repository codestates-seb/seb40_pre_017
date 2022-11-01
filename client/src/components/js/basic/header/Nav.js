import React, {useState, useEffect} from 'react';
import '../../../css/basic/header/nav.scss';
import Hamburger from './Hamburger';
import Logo from './Logo';
import Snav from './Snav';
import Navinput from './Navinput';
import Navbtn from './Navbtn';
import User from './User';
import { getItemWithExpireTime }  from '../../../../util/controlStorage'

export default function Nav({changeInputData}) {

  let data = getItemWithExpireTime("member");
  let login = getItemWithExpireTime("isLogin");

  return (
    <>
      <nav className='nav'>
        <Hamburger />
        <Logo />
        <Snav />
        <Navinput changeInputData={changeInputData} />
        {login ? <User data={data} /> : <Navbtn />}
      </nav>
    </>
  )
}
