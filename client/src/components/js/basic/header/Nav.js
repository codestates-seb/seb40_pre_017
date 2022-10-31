import React, {useState, useEffect} from 'react';
import '../../../css/basic/header/nav.scss';
import Hamburger from './Hamburger';
import Logo from './Logo';
import Snav from './Snav';
import Navinput from './Navinput';
import Navbtn from './Navbtn';
import User from './User';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faMagnifyingGlass } from "@fortawesome/free-solid-svg-icons";
import { getItemWithExpireTime }  from '../../../../util/controlStorage'

export default function Nav() {

  let data = getItemWithExpireTime("member");
  let login = getItemWithExpireTime("isLogin");
  
  console.log(data)
  console.log(login)

  return (
    <>
      <nav className='nav'>
        <Hamburger />
        <Logo />
        <Snav />
        <Navinput />
        {login ? <User data={data} /> : <Navbtn />}
      </nav>
    </>
  )
}
