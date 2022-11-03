import React from 'react'
import '../../../css/basic/header/navbtn.scss';
import { Link, useLocation } from 'react-router-dom';


export default function Navbtn() {

  const location = useLocation();

  const btnClick = () => {
    if(location !== '/login' && location !== '/signup'){
      localStorage.setItem("lastPath", location.pathname + location.search);
    }
  }

  return (
    <div className='btnArea'>
      <Link className='btn btnColor1' onClick={btnClick} to={"/login"} >Log in</Link>
      <Link className='btn btnColor2' onClick={btnClick} to={"/signup"} >Sign up</Link>
    </div>
  )
}
