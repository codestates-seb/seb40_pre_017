import React from 'react';
import '../../../css/basic/header/nav.scss';
import Hamburger from './Hamburger';
import Logo from './Logo';
import Snav from './Snav';
import Navinput from './Navinput';
import { Link } from 'react-router-dom';


export default function Nav() {
  return (
    <nav className='nav'>
      <Hamburger />
      <Link to={'/'}>
        <Logo />
      </Link>
      <Snav />
      <Navinput/>
      <button>1</button>
    </nav>
  )
}
