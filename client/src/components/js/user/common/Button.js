import React from 'react'
import '../../../css/user/common/button.scss';

export default function Button({btnContent}) {
  return (
    <button className='btn btnColor'>{btnContent}</button>
  )
}
