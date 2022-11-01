import React, {useState, useEffect} from 'react'
import '../../../css/basic/header/navinput.scss';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faMagnifyingGlass } from "@fortawesome/free-solid-svg-icons";

import Inputtab from './Inputtab';
import Input from './Input';


export default function Navinput() {
  
  const [isFocused, setIsFocused] = useState(false);
  const [inputValue, setInputValue] = useState("");

  // 아이콘 클릭 시 인풋 포커스.
  const focusChange = (value) => {
    setIsFocused(value)
  }
  // input 데이터 관리
  const inputChange = (e) => {
    setInputValue(e.target.value)
    console.log(inputValue)
  }

  return (
    <form className='navInputArea' action='/search'>
      <label htmlFor="search" className='navIcon'>
        <FontAwesomeIcon className='hamburger' icon={faMagnifyingGlass} />
      </label>
      <Input name="q" inputChange={inputChange} focusChange={focusChange} id="search"/>

      {isFocused ? <div className='inputTabArea'>
        <Inputtab />
      </div> : ""}
    </form>
  )
}
