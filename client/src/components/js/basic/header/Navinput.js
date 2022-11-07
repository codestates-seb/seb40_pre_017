import React, {useState, useEffect} from 'react'
import '../../../css/basic/header/navinput.scss';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faMagnifyingGlass } from "@fortawesome/free-solid-svg-icons";

import Inputtab from './Inputtab';
import Input from './Input';


export default function Navinput({changeInputData}) {
  
  const [isFocused, setIsFocused] = useState(false);

  // 아이콘 클릭 시 인풋 포커스.
  const focusChange = (value) => {
    setIsFocused(value)
  }

  return (
    <form className='navInputArea' onSubmit={(e)=> e.preventDefault()}>
      <label htmlFor="search" className='navIcon'>
        <FontAwesomeIcon className='hamburger' icon={faMagnifyingGlass} />
      </label>
      <Input name="q" changeInputData={changeInputData} focusChange={focusChange} id="search"/>

      {isFocused ? <div className='inputTabArea'>
        <Inputtab />
      </div> : ""}
    </form>
  )
}
