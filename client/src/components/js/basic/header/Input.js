import React from 'react'
import '../../../css/basic/header/input.scss'


export default function Input({name, focusChange}) {
  return (
    <input 
    id={name} 
    onFocus={() => focusChange(true)} 
    onBlur={() =>focusChange(false)} 
    className={(name === "검색" ? " navInput": "navSmallInput")}
    type="text" 
    placeholder="Search…" />
  )
}
