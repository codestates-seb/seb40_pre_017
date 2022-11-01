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
      <button onClick={btnClick} className='btn btnColor1'>
        <Link to={"/login"} >Log in</Link>
      </button>
      <button className='btn btnColor2'>
        <Link onClick={btnClick} to={"/signup"} >Sign up</Link>
      </button>
    </div>
  )
}
