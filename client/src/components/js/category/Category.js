import React,{ useState, useEffect } from 'react';
import { Link, useLocation } from 'react-router-dom';
import '../../css/category/category.scss';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faEarthAmericas, faStar } from "@fortawesome/free-solid-svg-icons";
import {ReactComponent as TeamSVG} from '../../../assets/imgs/teams-illo-free-sidebar-promo.svg';
let navData = [["/","Questions"], ["/tags","Tags"], ["/users","Users"], ["/companies","Companies"]];

// 네비게이션 에러 있음.

export default function Category() {
  const [selectNav, setSelectNav] = useState(0);
  
  const location = useLocation().pathname;
  
  useEffect(() => {
    setSelectNav(location)
  },[location])

  return (
    <nav className='categoryArea'>
      <ol>
        <li className='navMain'>
          <Link to={'#'}>
            Home
          </Link>
        </li>
        <li>
          <ol>
            <li className='navMain'>PUBLIC</li>
              <li className={"navSub" + (navData[0][0] === selectNav ? " active": "")}>
                <Link to={navData[0][0]}>
                  <FontAwesomeIcon className='icon earth' icon={faEarthAmericas} />
                  {navData[0][1]}
                </Link>
              </li>
              <li className={"navSub" + (navData[1][0] === selectNav ? " active": "")}>
                <Link to="#">
                  {navData[1][1]}
                </Link>
              </li>
              <li className={"navSub" + (navData[2][0] === selectNav ? " active": "")}>
                <Link to="#">
                  {navData[2][1]}
                </Link>
              </li>
              <li className={"navSub" + (navData[3][0] === selectNav ? " active": "")}>
                <Link to="#">
                  {navData[3][1]}
                </Link>
              </li>

            <li className='navMain'>COLLECTIVES</li>
            <li className='navSub'>
              <Link to={'#'}>
                <FontAwesomeIcon className='icon star' icon={faStar} />
                Explore Collectives
              </Link>
            </li>
          </ol>
        </li>
        <li>
          <Link to={'#'} className="navMain">
            TEAMS
          </Link>
          <div className='teamArea'>
            <strong className='strongStyle'>Stack Overflow for Teams</strong>– Start collaborating and sharing
            organizational knowledge.
            <TeamSVG className="teamSvg" />
            <button className='btnColor1'>Create a free Team</button>
            <button className='btnColor2'>Why Teams?</button>
          </div>
        </li>
      </ol>
    </nav>
  )
}
