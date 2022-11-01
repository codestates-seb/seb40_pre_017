import React from 'react'
import '../../../css/basic/header/navbtn.scss';
import { Link, useLocation } from 'react-router-dom';


export default function Navbtn() {

  const location = useLocation().pathname;


  const btnClick = () => {
    if(location !== '/login' && location !== '/signup'){
      localStorage.setItem("lastPath", location);
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
