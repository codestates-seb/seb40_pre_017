import React from 'react'
import '../../../css/basic/header/input.scss'


export default function Input({name, searchQuestion, inputChange, id, focusChange}) {
  return (
    <input 
    id={id} 
    name={name}
    onFocus={() => focusChange(true)} 
    onBlur={() =>focusChange(false)} 
    className={(id === "search" ? " navInput": "navSmallInput")}
    type="text" 
    onChange={inputChange}
    onKeyPress={searchQuestion}
    placeholder="Search…" />
  )
}
