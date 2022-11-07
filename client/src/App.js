import './App.scss';
import React, {useState, useEffect} from 'react'
import { Route, Routes, useLocation, useNavigate } from 'react-router-dom';
import Layout from './components/js/basic/Layout';
import Notfound from './components/js/basic/Notfound';
import QuestionPage from './pages/QuestionPage';
import AddQuestion from './pages/AddQuestion'
import DetailPage from './pages/DetailPage'
import Login from './pages/Login'
import Signup from './pages/Signup'
import EditQuestion from './pages/EditQuestion';
import EditAnswer from './pages/EditAnswer';
import SearchPage from './pages/SearchPage';

const REACT_APP_API_URL = process.env.REACT_APP_API_URL;

function App() {
  const navigate = useNavigate();
  const location = useLocation();

  const [inputData, setInputData] = useState("");
  const [filterData, setFilterData] = useState("NoAnswer");
  
  const [islogined, setIslogined] = useState(false);

  const [memberData, setMemberData] = useState(JSON.parse(window.sessionStorage.getItem("member")));
  const [accessToken, setAccessToken] = useState(window.sessionStorage.getItem("jwtToken"));

  useEffect(() => {
    if(!(accessToken && memberData)){  
      console.log('reload')
      fetch(`${REACT_APP_API_URL}users/reissue`,{
        method: "GET",
        credentials: 'include'
      })
      .then((res) => {
        let jwtToken = res.headers.get("Authorization");
        setAccessToken(jwtToken);
        if(jwtToken) {
          setIslogined(true);
          window.sessionStorage.setItem("jwtToken", jwtToken);
        }
        return res.json()
      })
      .then((data) => {
        if(data.status !== 400) {
          setMemberData(data);
          window.sessionStorage.setItem("member", JSON.stringify(data));
        }
      })
      .catch((err) => {
        console.log(err)
      })

    }else{
      setIslogined(true)
    }
  }, [])

  const changeInputData = (e) => {
    if(e.target.value !== ""){
      let temp = e.target.value;
      setInputData(e.target.value);      
      e.target.value = "";
      navigate(`/search?q=${temp}`);
    }
  }

  const changeFilterData = (e) => {
    let temp = e.target.name;
    setFilterData(e.target.name)
    setPagetest({selected: 1})
    navigate(`/?filter=${temp}`);
  }

  const logoutControll = () => {
    fetch(`${REACT_APP_API_URL}users/logout`,{
      method: "DELETE",
      credentials: 'include'
    })
    .then((res) => {
      setIslogined(false);
      window.sessionStorage.removeItem("member");        
      window.sessionStorage.removeItem("jwtToken");        
      alert("Logout Success!!");
      window.location.href = "/";
    })
    .catch((err) =>{
      console.log(err)
    })
  }

  const [pagetest, setPagetest] = useState({selected: 1});
  const handlePageChange = (page) => {
    setPagetest({selected: page.selected + 1});
  };

  return (
    <>
      <Routes>
        <Route path="/" element={<Layout changeInputData={changeInputData} islogined={islogined} memberData={memberData} logoutControll={logoutControll} />}>
          <Route index element={<QuestionPage accessToken={accessToken} filterData={filterData} changeFilterData={changeFilterData} pagetest={pagetest} handlePageChange={handlePageChange}/>} />
          <Route path="/add" element={<AddQuestion accessToken={accessToken}/>} />
          <Route path="questions/:id" element={<DetailPage accessToken={accessToken} />} />
          <Route path="questions/:id/edit" element={<EditQuestion accessToken={accessToken}/>} />
          <Route path="questions/:id/editanswer/:answerId" element={<EditAnswer accessToken={accessToken}/>} />
          <Route path="/login" element={<Login setIslogined={setIslogined} setMemberData={setMemberData} setAccessToken={setAccessToken} />} />
          <Route path="/signup" element={<Signup />} />
          <Route path="*" element={<Notfound />} />
        </Route>
        <Route path="/search" element={<Layout changeInputData={changeInputData} islogined={islogined} memberData={memberData} logoutControll={logoutControll} />}>
          <Route index element={<SearchPage accessToken={accessToken} inputData={inputData} handlePageChange={handlePageChange} pagetest={pagetest}/>} />
          <Route path="*" element={<Notfound />} />
        </Route>
      </Routes>
    </>
  );
} 

export default App;
