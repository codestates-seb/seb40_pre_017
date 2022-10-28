import React from 'react'
import '../../css/addContent/AddContent.scss'

export default function AddContent({inputContent}) {
  return (
    <div>
        <input className='AddInput' type='text' onChange={inputContent}></input>
    </div>
  )
}
