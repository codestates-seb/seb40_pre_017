import React, {useEffect} from 'react'
import { useNavigate } from 'react-router-dom';

export default function Notfound() {

  let navigate = useNavigate();
  console.log("redirect")
  useEffect(()=>{
    navigate(sessionStorage.getItem('redirect'));
  }, [])
  return (
    <div>Notfound</div>
  )
}
