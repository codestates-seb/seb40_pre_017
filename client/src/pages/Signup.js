import React, {useState} from 'react'
import './signup.scss';
import { Link } from 'react-router-dom';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faMessage, faUpDown, faTags, faTrophy } from "@fortawesome/free-solid-svg-icons";
import Googlebtn from '../components/js/user/common/Googlebtn';
import Githubbtn from '../components/js/user/common/Githubbtn';
import Facebookbtn from '../components/js/user/common/Facebookbtn';
import Input from '../components/js/user/common/Input';
import Button from '../components/js/user/common/Button';
import Inputerror from '../components/js/user/common/Inputerror';


let content = ["Log in with Google", "Log in with Github", "Log in with Facebook"];
// 이메일 형식이여야 함.
let emailExptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
//  비밀번호에 문자, 숫자, 특수문자가 각각 최소 1개 이상은 들어있어야 되고, 최소 8자리에서 최대 16자리.
let passwordExptext = /^(?=.*[a-zA-Z])((?=.*\d)(?=.*\W)).{8,16}$/;


export default function Signup() {

  // 어떤 경로에서 로그인 페이지로 접속했는지 확인 후 로그인 성공시 해당 페이지로 이동.
  // 닉네임 입력 검사 -> 닉네임 입효성 판단 boolean -> p태그 보여줌
  // 이메일 유효성 검사 -> 이메일 유효성 판단 boolean -> p태그 보여줌
  // 이메일 입력 길이 먼저 검사.
  // 입력한 값이 있으면 이메일 주소 형식인지 확인.
  // 비밀번호 유효성 검사 -> 비밀번호 유효성 판단 boolean -> p태그 보여줌

  const [data, setDate] = useState({});

  const [emailError, setEmailError] = useState(false);
  const [passwordError, setPasswordError] = useState(false);

  const formSubmit = (e) =>{
    console.log(data)
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
      console.log('제출')
      // fetch("http://localhost:3001/users", {
      //   method: "POST",
      //   headers: {"Content-Type" : "application/json"},
      //   body: JSON.stringify(data)
      // })
      // .then(() => {
      //   // localStorage.setItem("lastPath", "/");
        // alert("Sign up Success!!")
        // window.location.href = "/login";
      // })
      // .catch((error) => {
        // alert("error")

      //     console.error('Error', error);
      // })
    }
    
  }

  const onChangeInput = (e) => {
    setDate({...data, [e.target.name] : e.target.value});
    console.log(emailError)
    console.log(passwordError)
    // 이메일 유효성 체크

    // 이메일 유효성이 확인되면 폼에서 생성한 에러 메세지 삭제.
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
            {/* <p>By clicking “Sign up”, you agree to our terms of service, privacy policy and cookie policy</p> */}
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
