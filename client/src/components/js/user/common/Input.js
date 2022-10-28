import React from 'react'
import '../../../css/user/common/input.scss';

export default function Input({labelName, inputId, inputType}) {
  return (
    <div className='inputArea'>
      <label for={inputId}>{labelName}</label>
      <input className='formInput' id={inputId} type={inputType} />
    </div>
  )
}
