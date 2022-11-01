import React from 'react';
import '../../../css/basic/header/header.scss'
import Nav from './Nav';


export default function Header({changeInputData}) {
  return (
    <header className='header'>
      <Nav changeInputData={changeInputData} />
    </header>
  )
}
