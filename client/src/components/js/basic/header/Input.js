import React from 'react'
import '../../../css/basic/header/input.scss'


export default function Input({name, changeInputData, id, focusChange}) {
  return (
    <input 
    id={id} 
    name={name}
    onFocus={() => focusChange(true)} 
    onBlur={() =>focusChange(false)} 
    className={(id === "search" ? " navInput": "navSmallInput")}
    type="text" 
    onKeyPress={(e)=> {
      if(e.charCode === 13){
        changeInputData(e)
      }
    }}
    placeholder="Search…" />
  )
}
