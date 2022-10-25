import React from 'react';
import '../../../css/basic/header/nav.scss';
import Hamburger from './Hamburger';
import Logo from './Logo';
import Snav from './Snav';
import Navinput from './Navinput';


export default function Nav() {
  return (
    <nav className='nav'>
      <Hamburger />
      <Logo />
      <Snav />
      <Navinput/>
      <button>1</button>
    </nav>
  )
}
