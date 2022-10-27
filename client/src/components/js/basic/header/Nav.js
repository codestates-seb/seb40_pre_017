import React, {useState, useEffect} from 'react';
import '../../../css/basic/header/nav.scss';
import Hamburger from './Hamburger';
import Logo from './Logo';
import Snav from './Snav';
import Navinput from './Navinput';
import Navbtn from './Navbtn';
import Inputtab from './Inputtab';
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
      </nav>
      {/* <div className='navSmallInputArea'>
        {isFocused ?
        <label for="검색" className='navIcon'>
          <FontAwesomeIcon className='hamburger' icon={faMagnifyingGlass} />
        </label> : ""
        }
        {isFocused && <input onFocus={focusChange} onBlur={focusChange} id='검색' className='navInput' type="text" placeholder="Search…" />}
        {isFocused && <div className='navSmallInputTabArea'><Inputtab /></div>}
      </div> */}
    </>
  )
}
