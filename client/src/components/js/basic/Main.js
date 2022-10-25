import React from 'react'
import { Outlet } from 'react-router-dom';
import '../../css/basic/main.scss';

export default function Main() {
  return (
    <main className='main'>
      <Outlet />
    </main>
  )
}