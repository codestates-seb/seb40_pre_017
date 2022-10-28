import React from 'react'
import Footer from './Footer'
import Main from './Main'
import Header from './header/Header'
import { Outlet } from 'react-router-dom';
import '../../css/basic/Layout.scss';


export default function Layout() {
  return (
    <div className='appLayout'>
      <Header/>
      <div></div>
      <Main>
        <Outlet/>
      </Main>
      <Footer/>
    </div>
  )
}
