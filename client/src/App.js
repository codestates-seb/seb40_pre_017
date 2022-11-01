import './App.scss';
import { Route, Routes } from 'react-router-dom';
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

function App() {
  // json-server --watch data.json --port 3001
  // 라우트 경로 에러 있음
  
  // 필요한 페이지에서 불러오기?
  const [items] = useFetch("http://localhost:3001/items/");

  return (
    <>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<QuestionPage items={items}/>} />
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
        <Route path="/search" element={<Layout />}>
          <Route index element={<SearchPage items={items}/>} />
        </Route>
      </Routes>
    </>
  );
} 

export default App;
