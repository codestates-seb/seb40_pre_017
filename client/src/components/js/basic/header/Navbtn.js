import React from 'react'
import '../../../css/basic/header/navbtn.scss';
import { Link } from 'react-router-dom';


export default function Navbtn() {
  return (
    <div className='btnArea'>
      <button className='btn btnColor1'>
        <Link to={"/login"} >Log in</Link>
      </button>
      <button className='btn btnColor2'>
        <Link to={"/signup"} >Sign up</Link>
      </button>
    </div>
  )
}
