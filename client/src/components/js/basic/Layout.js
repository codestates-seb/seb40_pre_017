import React from 'react'
import Footer from './Footer'
import Main from './Main'
import Header from './header/Header'
import { Outlet } from 'react-router-dom';
import '../../css/basic/Layout.scss';
import { Link, useLocation } from 'react-router-dom';



export default function Layout() {

  const location = useLocation().pathname;

  console.log(location)

  return (
    <>
      {(location === '/login') || (location === '/signup') ? 
        <div className='loginLayout'>
          <Header/>
          <div></div>
          <Main>
            <Outlet/>
          </Main>
        </div> : 
        <div className='appLayout'>
          <Header/>
          <div></div>
          <Main>
            <Outlet/>
          </Main>
          <Footer/>
        </div>}
    </>
  )
}
