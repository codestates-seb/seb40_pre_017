import React, {useState, useEffect} from 'react'
import '../../../css/basic/header/navinput.scss';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faMagnifyingGlass } from "@fortawesome/free-solid-svg-icons";
import Inputtab from './Inputtab';
import Input from './Input';


export default function Navinput() {

  const [isFocused, setIsFocused] = useState(false);

  const focusChange = (value) => {
    setIsFocused(value)
  }

  return (
    <form className='navInputArea'>

      {/* display가 640px 이상일 때 보임 */}
      <label for="검색" className='navIcon'>
        <FontAwesomeIcon className='hamburger' icon={faMagnifyingGlass} />
      </label>

      {/* display가 640px 이하일 때 보임
      <label for="검색" className='navIconSmall' onClick={()=> focusChange(true)} >
        <FontAwesomeIcon className='hamburger' icon={faMagnifyingGlass} />
      </label> */}
      <Input focusChange={focusChange} name="검색"/>

      {isFocused ? <div className='inputTabArea'>
        <Inputtab />
      </div> : ""}
    </form>
  )
}
