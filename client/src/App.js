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
  
  const [inputData, setInputData] = useState("");
  const [filterData, setFilterData] = useState("newest");
  
  const changeInputData = async(e) => {
    if(e.target.value !== ""){
      let temp = e.target.value;
      setInputData(e.target.value);      
      e.target.value = "";
      navigate('/search?' + ('q:' + temp) + ('&tab:' + filterData));
    }
  }
  
  const changeFilterData = (e) => {
    let temp = e.target.name;
    setFilterData(e.target.name)
    // 좋은 방법?
    if(location !== "/"){
      navigate('/search?' + ('q:' + inputData) + ('&tab:' + temp));
    }else{
      navigate('/?' + ('&tab:' + temp));
    }

  }

  // 필요한 페이지에서 불러오기?
  const [items] = useFetch("http://localhost:3001/items/");

  return (
    <>
      <Routes>
        <Route path="/" element={<Layout changeInputData={changeInputData} />}>
          <Route index element={<QuestionPage filterData={filterData} changeFilterData={changeFilterData} />} />
          <Route path="/add" element={<AddQuestion />} />
          <Route path="questions/:id" element={<DetailPage items={items}/>} />
          <Route path="questions/:id/edit" element={<EditQuestion items={items}/>} />
          <Route path="questions/:id/editanswer/:answerId" element={<EditAnswer items={items}/>} />
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<Signup />} />
          {/* path 확실하게 재수정 */}
          {/* 검색 페이지 라우팅 */}
          {/* 경로 예외처리 */}
          <Route path="*" element={<Notfound />} />
        </Route>
        <Route path="/search" element={<Layout changeInputData={changeInputData} />}>
          <Route index element={<SearchPage inputData={inputData} filterData={filterData} changeFilterData={changeFilterData} />} />
        </Route>
      </Routes>
    </>
  );
} 

export default App;
