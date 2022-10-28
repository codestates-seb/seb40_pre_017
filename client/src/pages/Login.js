import React from 'react'
import './login.scss';
import { Link } from 'react-router-dom';


export default function login() {
  return (
    <div className='loginPage'>
      <div className='logo'>
        <Link className='logoText' to={"/"} />
      </div>
    </div>
  )
}
