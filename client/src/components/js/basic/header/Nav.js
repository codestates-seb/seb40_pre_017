import React from 'react';
import '../../../css/basic/header/nav.scss';
import Hamburger from './Hamburger';
import Logo from './Logo';
import Snav from './Snav';
import Navinput from './Navinput';
import Navbtn from './Navbtn';

export default function Nav() {
  return (
    <nav className='nav'>
      <Hamburger />
      <Logo />
      <Snav />
      <Navinput/>
      <Navbtn />
    </nav>
  )
}
