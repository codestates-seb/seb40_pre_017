import React from 'react'
import './signup.scss';
import { Link } from 'react-router-dom';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faMessage, faUpDown, faTags, faTrophy } from "@fortawesome/free-solid-svg-icons";
import Googlebtn from '../components/js/user/common/Googlebtn';
import Githubbtn from '../components/js/user/common/Githubbtn';
import Facebookbtn from '../components/js/user/common/Facebookbtn';
import Input from '../components/js/user/common/Input';
import Button from '../components/js/user/common/Button';


let content = ["Log in with Google", "Log in with Github", "Log in with Facebook"];


export default function signup() {

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

          <form className='signupForm' action="#">
            <Input labelName="Display name" inputId="text" inputType="text" />
            <Input labelName="Email" inputId="email" inputType="email" />
            <Input labelName="Password" inputId="password" inputType="password" />
            <p>Passwords must contain at least eight characters, including at least 1 letter and 1 number.</p>
            <div className='checkBot'></div>
            <Button btnContent="Sign up" />
            {/* <p>By clicking “Sign up”, you agree to our terms of service, privacy policy and cookie policy</p> */}
          </form>

          <div className='loginLink'>
            Already have an account?
            <Link to={"/"} >Log in</Link>
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
