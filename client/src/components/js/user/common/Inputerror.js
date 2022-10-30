import React from 'react'
import '../../../css/user/common/inputerror.scss';

export default function Inputerror({text}) {
  return (
    <p className='inputMessage'>{text}</p>
  )
}
