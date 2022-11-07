import React from 'react'
import '../../css/basic/footer.scss';
import { Link } from 'react-router-dom';


export default function Footer() {
  return (
    <footer className='footer'>
      <div className='footerInner'>
        <div className='footerLogo'>
          <div className='logo'>
            <Link className='logoText' to={"/"} />
          </div>
        </div>
        <div className='footerNav'>
          <div className='footerNavCol'>
            <h5 className='title'>
            <Link to={'#'}>STACK OVERFLOW</Link>
            </h5>
            <ul>
              <li><Link to={'#'}>Questions</Link></li>
              <li><Link to={'#'}>Help</Link></li>
            </ul>
          </div>
          <div className='footerNavCol'>
            <h5 className='title'>
              <Link to={'#'}>PRODUCTS</Link>
            </h5>
            <ul>
              <li><Link to={'#'}>Teams</Link></li>
              <li><Link to={'#'}>Advertising</Link></li>
              <li><Link to={'#'}>Collectives</Link></li>
              <li><Link to={'#'}>Talent</Link></li>
            </ul>
          </div>
          <div className='footerNavCol'>
            <h5 className='title'>
              <Link to={'#'}>COMPANY</Link>
            </h5>
            <ul>
              <li><Link to={'#'}>About</Link></li>
              <li><Link to={'#'}>Press</Link></li>
              <li><Link to={'#'}>Work Here</Link></li>
              <li><Link to={'#'}>Legal</Link></li>
              <li><Link to={'#'}>Privacy Policy</Link></li>
              <li><Link to={'#'}>Terms of Service</Link></li>
              <li><Link to={'#'}>Contact Us</Link></li>
              <li><Link to={'#'}>Cookie Settings</Link></li>
              <li><Link to={'#'}>Cookie Policy</Link></li>
            </ul>
          </div>
          <div className='footerNavCol'>
            <h5 className='title'>
              <Link to={'#'}>STACK EXCHANGE NETWORK</Link>
            </h5>
            <ul>
              <li><Link to={'#'}>Technology</Link></li>
              <li><Link to={'#'}>Culture & recreation</Link></li>
              <li><Link to={'#'}>Life & arts</Link></li>
              <li><Link to={'#'}>Science</Link></li>
              <li><Link to={'#'}>Professional</Link></li>
              <li className='blank'><Link to={'#'}>Business</Link></li>
              <li><Link to={'#'}>API</Link></li>
              <li><Link to={'#'}>Data</Link></li>
            </ul>
          </div>
        </div>
        <div className='footerCopyright'>
          <ul className='footerCopyrightCol'>
            <li>
              <Link to={'#'}>Blog</Link>
            </li>
            <li>
              <Link to={'#'}>Facebook</Link>

            </li>
            <li>
              <Link to={'#'}>Twitter</Link>

            </li>
            <li>
              <Link to={'#'}>Linkedin</Link>

            </li>
            <li>
              <Link to={'#'}>Instagram</Link>

            </li>
          </ul>
          <p className='footerCopyrightCol'>Site design / logo © 2022 Stack Exchange Inc; user contributions licensed under CC BY-SA. rev 2022.10.27.42995</p>
        </div>
      </div>
    </footer>
  )
}
