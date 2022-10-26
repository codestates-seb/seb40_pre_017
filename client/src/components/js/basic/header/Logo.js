import React from 'react'
import '../../../css/basic/header/logo.scss';
import { Link } from 'react-router-dom';


export default function Logo() {
  return (
    <Link className='logoArea' to={'/'}>
      <div className='logo' />
    </Link>
  )
}
