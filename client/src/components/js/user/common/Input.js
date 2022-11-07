import React from 'react'
import '../../../css/user/common/input.scss';

export default function Input({labelName, inputId, inputType, name, onChangeInput}) {
  return (
    <div className='inputArea'>
      <label className='formLabel' htmlFor={inputId}>{labelName}</label>
      <input name={name} className='formInput' id={inputId} type={inputType} onChange={onChangeInput} />
    </div>
  )
}
