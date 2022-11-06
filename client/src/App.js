import './App.scss';
import React, {useState, useEffect} from 'react'
import { Route, Routes, useNavigate } from 'react-router-dom';
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

// json-server --watch data.json --port 3001

function App() {
  const navigate = useNavigate();

  const [inputData, setInputData] = useState("");
  const [filterData, setFilterData] = useState("NoAnswer");
  
  const [islogined, setIslogined] = useState(false);
  const [memberData, setMemberData] = useState({
    email: '',
    username: '',
    imageUrl: "https://i.imgur.com/GvsgVco.jpeg"
  });
  const [accessToken, setAccessToken] = useState(null);

  useEffect(() => {
    if(!(islogined && accessToken)){
      console.log('reload')
      window.localStorage.removeItem("member");        
      fetch('/api/users/reissue',{
        method: "GET",
      })
      .then((res) => {
        let jwtToken = res.headers.get("Authorization");
        setAccessToken(jwtToken);
        if(jwtToken) {
          setIslogined(true);
        }
        return res.json()
      })
      .then((data) => {
        if(data.status !== 400) {
          setMemberData(data);
          localStorage.setItem("member", data.username);
        }
      })
      .catch((err) => {
        console.log(err)
      });
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
    navigate(`/?filter=${temp}`);
  }

  const logoutControll = () => {
    fetch('/api/users/logout',{
      method: "DELETE",
      headers: new Headers({
        "ngrok-skip-browser-warning": "69420"
      })
    })
    .then((res) => {
      setMemberData(null);
      setAccessToken(null);
      setIslogined(false);
      window.localStorage.removeItem("member");        

      alert("Logout Success!!");
      window.location.href = "/";
    })
    .catch((err) =>{
      console.log(err)
    })
  }

  return (
    <>
      <Routes>
        <Route path="/" element={<Layout changeInputData={changeInputData} islogined={islogined} memberData={memberData} logoutControll={logoutControll} />}>
          <Route index element={<QuestionPage accessToken={accessToken} filterData={filterData} changeFilterData={changeFilterData}/>} />
          <Route path="/add" element={<AddQuestion accessToken={accessToken}/>} />
          <Route path="questions/:id" element={<DetailPage accessToken={accessToken} />} />
          <Route path="questions/:id/edit" element={<EditQuestion accessToken={accessToken}/>} />
          <Route path="questions/:id/editanswer/:answerId" element={<EditAnswer accessToken={accessToken}/>} />
          <Route path="/login" element={<Login setIslogined={setIslogined} setMemberData={setMemberData} setAccessToken={setAccessToken} />} />
          <Route path="/signup" element={<Signup />} />
          <Route path="*" element={<Notfound />} />
        </Route>
        <Route path="/search" element={<Layout changeInputData={changeInputData} islogined={islogined} memberData={memberData} logoutControll={logoutControll} />}>
          <Route index element={<SearchPage accessToken={accessToken} inputData={inputData} />} />
          <Route path="*" element={<Notfound />} />
        </Route>
      </Routes>
    </>
  );
} 

export default App;
