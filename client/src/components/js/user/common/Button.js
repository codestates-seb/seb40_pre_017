import React from 'react'
import '../../../css/user/common/button.scss';

export default function Button({formSubmit, btnContent}) {
  return (
    <button onClick={formSubmit} type='submit' className='btn btnColor'>{btnContent}</button>
  )
}
