import React from 'react'
import '../../../css/basic/header/input.scss'


export default function Input({id, focusChange}) {
  return (
    <input 
    id={id} 
    onFocus={() => focusChange(true)} 
    onBlur={() =>focusChange(false)} 
    className={(id === "search" ? " navInput": "navSmallInput")}
    type="text" 
    placeholder="Search…" />
  )
}
