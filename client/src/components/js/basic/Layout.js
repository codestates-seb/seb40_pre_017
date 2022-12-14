import React, {useState, useEffect} from 'react'
import Footer from './Footer'
import Main from './Main'
import Header from './header/Header'
import { Outlet } from 'react-router-dom';
import '../../css/basic/Layout.scss';
import { Link, useLocation } from 'react-router-dom';


export default function Layout({changeInputData, islogined, memberData, logoutControll}) {

  const location = useLocation().pathname;
  let pageStyle = (location === '/login') || (location === '/signup');

  return (
    <>
      {pageStyle ? 
        <div className='loginLayout'>
          <Header />
          <div />
          <Main pageStyle={pageStyle}>
            <Outlet/>
          </Main>
        </div> : 
        <div className='appLayout'>
          <Header changeInputData={changeInputData} islogined={islogined} memberData={memberData} logoutControll={logoutControll} />
          <div />
          <Main>
            <Outlet/>
          </Main>
          <Footer/>
        </div>}
    </>
  )
}
