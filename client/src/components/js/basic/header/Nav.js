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


export default function Nav() {

  return (
    <>
      <nav className='nav'>
        <Hamburger />
        <Logo />
        <Snav />
        <Navinput />
        <Navbtn />

        {/* props */}
        {/* <User /> */}

      </nav>
    </>
  )
}
