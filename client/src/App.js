import './App.scss';
import React, {useState, useEffect} from 'react'
import { Route, Routes, useNavigate, useLocation } from 'react-router-dom';
import Layout from './components/js/basic/Layout';
import Notfound from './components/js/basic/Notfound';
import QuestionPage from './pages/QuestionPage';
import AddQuestion from './pages/AddQuestion'
import DetailPage from './pages/DetailPage'
import Login from './pages/Login'
import Signup from './pages/Signup'
import useFetch from './util/useFetch';
import EditQuestion from './pages/EditQuestion';
import EditAnswer from './pages/EditAnswer';
import SearchPage from './pages/SearchPage';
// json-server --watch data.json --port 3001

function App() {
  const location = useLocation().pathname;
  const navigate = useNavigate();
  // 검색 state
  const [inputData, setInputData] = useState("");
  const [filterData, setFilterData] = useState("newest");
  
  // 로그인 state
  const [islogined, setIslogined] = useState(false);
  const [memberData, setMemberData] = useState(null);
  const [accessToken, setAccessToken] = useState(null);

  useEffect(() => {
    if(!(islogined && memberData && accessToken)){
      console.log('reload')
    //   fetch('http://localhost:3001/users/reissue',{
    //     method: "GET",
    //     headers: {
    //       "ngrok-skip-browser-warning": "skip"
    //     }
    //   })
    //   .then((res) => {
    //     // access 토큰 재발급 후 로컬 스토리지에 저장.
    //     let jwtToken = res.headers.authorization;
    //     setAccessToken(jwtToken);
    //     return res.json()
    //   })
    //   .then((data) => {
    //     setIslogined(true);
    //     setMemberData({
    //       "memberName": "qwer1234",
    //       "memberEmail" : "qwer1234@gmail.com"
    //     });
    //     // setTimeout으로 갱신 함수 만들면?
    //   })
    //   .catch((err) => {
    //     // 리프레시 토큰이 유효하지 않을 경우.
    //     // 로그아웃 호출
    //     fetch('http://localhost:3001/users/logout',{
    //       method: "DELETE",
    //       headers: {
    //         "ngrok-skip-browser-warning": "skip"
    //       }
    //     })
    //     .then((res) => {
    //       alert("Logout")
    //       navigate("/")
    //     })
    //   });
    }
  }, [])

  // 검색 함수
  const changeInputData = async(e) => {
    if(e.target.value !== ""){
      let temp = e.target.value;
      setInputData(e.target.value);      
      e.target.value = "";
      navigate('/search?' + ('q:' + temp) + ('&tab:' + filterData));
    }
  }
  // 필터 함수
  const changeFilterData = (e) => {
    let temp = e.target.name;
    setFilterData(e.target.name)

    if(location !== "/"){
      navigate('/search?' + ('q:' + inputData) + ('&tab:' + temp));
    }else{
      navigate('/?' + ('&tab:' + temp));
    }
  }
  // 로그아웃
  const logoutControll = () => {
    // fetch('http://localhost:3001/users/logout')
    // .then((res) => {
      setMemberData(null);
      setAccessToken(null);
      setIslogined(false);
      alert("Logout Success!!");
      navigate("/");
    // })
  }



  // 필요한 페이지에서 불러오기?
  const [items] = useFetch("http://localhost:3001/items/");
  
  return (
    <>
      <Routes>
        <Route path="/" element={<Layout changeInputData={changeInputData} islogined={islogined} memberData={memberData} logoutControll={logoutControll} />}>
          <Route index element={<QuestionPage filterData={filterData} changeFilterData={changeFilterData} />} />
          <Route path="/add" element={<AddQuestion />} />
          <Route path="questions/:id" element={<DetailPage items={items}/>} />
          <Route path="questions/:id/edit" element={<EditQuestion items={items}/>} />
          <Route path="questions/:id/editanswer/:answerId" element={<EditAnswer items={items}/>} />
          <Route path="/login" element={<Login setIslogined={setIslogined} setMemberData={setMemberData} setAccessToken={setAccessToken} />} />
          <Route path="/signup" element={<Signup />} />
          {/* path 확실하게 재수정 */}
          {/* 검색 페이지 라우팅 */}
          {/* 경로 예외처리 */}
          <Route path="*" element={<Notfound />} />
        </Route>

        <Route path="/search" element={<Layout changeInputData={changeInputData} islogined={islogined} memberData={memberData} logoutControll={logoutControll} />}>
          <Route index element={<SearchPage inputData={inputData} filterData={filterData} changeFilterData={changeFilterData} />} />
        </Route>
      </Routes>
    </>
  );
} 

export default App;
