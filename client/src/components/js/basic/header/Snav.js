import React from 'react'
import '../../../css/basic/header/snav.scss'


export default function Snav() {
  return (
    <ol className='sNavArea'>
      <li className='changeVisible'>About</li>
      <li>Products</li>
      <li className='changeVisible'>For Teams</li>
    </ol>
  )
}
