import React, {useState} from 'react'
import './login.scss';
import { Link, useNavigate } from 'react-router-dom';
import Googlebtn from '../components/js/user/common/Googlebtn';
import Githubbtn from '../components/js/user/common/Githubbtn';
import Facebookbtn from '../components/js/user/common/Facebookbtn';
import Input from '../components/js/user/common/Input';
import Button from '../components/js/user/common/Button';
import Inputerror from '../components/js/user/common/Inputerror';

let content = ["Log in with Google", "Log in with Github", "Log in with Facebook"];

export default function Login({setIslogined, setMemberData, setAccessToken}) {
  const navigate = useNavigate();

  const [data, setDate] = useState({});
  const [emailError, setEmailError] = useState(false);
  const [passwordError, setPasswordError] = useState(false);

  const formSubmit = (e) =>{
    e.preventDefault()
    let error = false

    if(data.email === undefined){
      setEmailError(true);
      error = true
    }
    if(data.password === undefined){
      setPasswordError(true);
      error = true
    }

    if(!error){
      fetch("/api/users/login", {
        method: "POST",
        headers: new Headers({
          "ngrok-skip-browser-warning": "69420",
          "Content-Type" : "application/json"
        }),
        body: JSON.stringify(data)
      })
      .then((res) => {
        if(res.status === 200){
          let jwtToken = res.headers.get("Authorization");
          setAccessToken(jwtToken)
        }       
        return res.json();
      })
      .then((resData) => {
        if(resData.status !== 401) {
          setMemberData(resData);
          localStorage.setItem("member", resData.username);
          setIslogined(true);
          navigate(localStorage.getItem('lastPath'));
        }else{
          alert("Please check your ID and password");
        }
      })
    }
  }

  const onChangeInput = (e) => {
    setDate({...data, [e.target.name] : e.target.value});

    if(data.email !== undefined){
      setEmailError(false);
    }
    if(data.password !== undefined){
      setPasswordError(false);
    }
  }

  return (
    <div className='loginPage'>
      <div className='loginPageInner'>
        <div className='logo'>
          <Link className='logoText' to={"/"} />
        </div>
        <Googlebtn content={content[0]} />
        <Githubbtn content={content[1]} />
        <Facebookbtn content={content[2]} />
        
        <form className='loginForm'>
          <Input labelName="Email" inputId="email" inputType="email" name="email" onChangeInput={onChangeInput} />
          {emailError && <Inputerror text="Email cannot be empty." />}

          <Input labelName="Password" inputId="password" inputType="password" name="password" onChangeInput={onChangeInput} />
          {passwordError && <Inputerror text="Password cannot be empty." />}

          <Button formSubmit={formSubmit} btnContent="Log in" />
        </form>
        <div className='loginLink'>
          Don’t have an account?
          <Link to={"/signup"} >Sign up</Link>
        </div>
        <div className='loginLink'>
          Are you an employer?
          <Link to={"/"} >Sign up on Talent</Link>
        </div>
      </div>
    </div>
  )
}
