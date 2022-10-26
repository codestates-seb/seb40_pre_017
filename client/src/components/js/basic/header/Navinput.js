import React from 'react'
import '../../../css/basic/header/navinput.scss';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faMagnifyingGlass } from "@fortawesome/free-solid-svg-icons";
export default function Navinput() {
  return (
    <div className='navInputArea'>
      <div className='navIcon'>
        <FontAwesomeIcon className='hamburger' icon={faMagnifyingGlass} />
      </div>
      <input className='navInput' type="text" placeholder="Search…" />
    </div>
  )
}
