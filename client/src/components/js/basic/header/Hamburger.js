import React, {useState} from 'react'
import '../../../css/basic/header/hamburger.scss';
// 리액트에서 사용하기 위해 추가
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
// 솔리드 아이콘
import { faBars, faXmark } from "@fortawesome/free-solid-svg-icons";


export default function Hamburger() {

  const [toggleOpen, setToggleOpen] = useState(false);


  return (
    <div className='hamburgerArea'>
      {toggleOpen ? <FontAwesomeIcon className='hamburger' icon={faXmark} /> : <FontAwesomeIcon className='hamburger' icon={faBars} />}
    </div>
  )
}
