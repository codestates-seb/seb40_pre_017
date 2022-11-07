import React from 'react'
import { Outlet } from 'react-router-dom';
import '../../css/basic/main.scss';

export default function Main({pageStyle}) {
  return (
    <main className={(pageStyle ? 'userMain' : 'main')}>
      <Outlet />
    </main>
  )
}