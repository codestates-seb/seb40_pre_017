import React from 'react'
import '../../css/basic/header.scss';
import { Link } from 'react-router-dom';
export default function Header() {
  return (
    <header className='header'>Header

    {/* 임시 홈 이동 버튼 */}
    <button>
          <Link to={'/'}>Home</Link>
    </button>
    {/* 임시 질문목록 이동 버튼 */}
      <button>
          <Link to={'/questions'}>Question</Link>
      </button>
    </header>
  )
}
