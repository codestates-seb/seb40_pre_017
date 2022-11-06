import React, {useState} from 'react'
import './signup.scss';
import { Link, useNavigate } from 'react-router-dom';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faMessage, faUpDown, faTags, faTrophy } from "@fortawesome/free-solid-svg-icons";
import Googlebtn from '../components/js/user/common/Googlebtn';
import Githubbtn from '../components/js/user/common/Githubbtn';
import Facebookbtn from '../components/js/user/common/Facebookbtn';
import Input from '../components/js/user/common/Input';
import Button from '../components/js/user/common/Button';
import Inputerror from '../components/js/user/common/Inputerror';


let content = ["Log in with Google", "Log in with Github", "Log in with Facebook"];
let emailExptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
let passwordExptext = /^(?=.*[a-zA-Z])((?=.*\d)(?=.*\W)).{8,16}$/;

const REACT_APP_API_URL = process.env.REACT_APP_API_URL;

export default function Signup() {
  const navigate = useNavigate();

  const [data, setDate] = useState({});

  const [emailError, setEmailError] = useState(false);
  const [passwordError, setPasswordError] = useState(false);

  const formSubmit = (e) =>{
    e.preventDefault()

    let error = false
    
    if(!emailExptext.test(data.email)){
      setEmailError(true);
      error = true;
    }
    if(!passwordExptext.test(data.password)){
      setPasswordError(true);
      error = true;
    }

    if(!error){
      fetch(`${REACT_APP_API_URL}users`, {
        method: "POST",
        body: JSON.stringify(data),
        credentials: 'include'
      })
      .then((res) => {
        if(res.status === 201) {
          localStorage.setItem("lastPath", "/");
          alert("Sign up Success!!")
          navigate("/login");
        }else{
          return res.json()
        }
      })
      .then(data => {
        alert(data.message)
      })
    }
    
  }

  const onChangeInput = (e) => {
    setDate({...data, [e.target.name] : e.target.value});
    if(emailExptext.test(data.email)){
      setEmailError(false);
    }
    if(passwordExptext.test(data.password)){
      setPasswordError(false);
    }
  }

  return (
    <div className='signupPage'>
      <div className='flexBox'>
        <div className='flexLeft'>
          <h1>Join the Stack Overflow community</h1>
          <div className='dFlex'>
            <FontAwesomeIcon className='flexItem color--blue' icon={faMessage} />
            {/* <div className='flexItem color--blue'>123</div> */}
            <div className='flexItem'>Get unstuck — ask a question</div>
          </div>
          <div className='dFlex'>
            <FontAwesomeIcon className='flexItem color--blue' icon={faUpDown} />
            <div className='flexItem'>Unlock new privileges like voting and commenting</div>
          </div>
          <div className='dFlex'>
            <FontAwesomeIcon className='flexItem color--blue' icon={faTags} />
            <div className='flexItem'>Save your favorite tags, filters, and jobs</div>
          </div>
          <div className='dFlex'>
            <FontAwesomeIcon className='flexItem color--blue' icon={faTrophy} />
            <div className='flexItem'>Earn reputation and badges</div>
          </div>
          <div className='tFlex'>
            <div className='flexItem'>Collaborate and share knowledge with a private group for FREE.</div>
            <Link to={"#"} className='flexItem color--blue' >Get Stack Overflow for Teams free for up to 50 users.</Link>
          </div>

        </div>
        <div className='flexRight'>
          <Googlebtn content={content[0]} />
          <Githubbtn content={content[1]} />
          <Facebookbtn content={content[2]} />

          <form className='signupForm'>
            <Input labelName="Display name" inputId="text" inputType="text" name="username" onChangeInput={onChangeInput} />
            <Input labelName="Email" inputId="email" inputType="email" name="email" onChangeInput={onChangeInput} />
            {emailError && <Inputerror text="The email is not a valid email address." />}

            <Input labelName="Password" inputId="password" inputType="password" name="password" onChangeInput={onChangeInput} />
            {passwordError && <Inputerror text="The password is not a valid password." />}

            <p>Passwords must contain at least eight characters, including at least 1 letter and 1 number.</p>
            <div className='checkBot'></div>
            <Button formSubmit={formSubmit} btnContent="Sign up" />
          </form>

          <div className='loginLink'>
            Already have an account?
            <Link to={"/login"} >Log in</Link>
          </div>
          <div className='loginLink'>
            Are you an employer?
            <Link to={"/"} >Sign up on Talent</Link>
          </div>

        </div>
      </div>
    </div>
  )
}
