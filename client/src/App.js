import './App.scss';
import { Route, Routes } from 'react-router-dom';
import Home from './pages/Home';
import Layout from './components/js/basic/Layout';
import Notfound from './components/js/basic/Notfound';
import QuestionPage from './pages/QuestionPage';
import AddQuestion from './pages/AddQuestion'
import DetailPage from './pages/DetailPage'
import Login from './pages/Login'
import Signup from './pages/Signup'

function App() {
  // json-server --watch data.json --port 3001
  return (
    <>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<Home />} />
          <Route path="/questions" element={<QuestionPage />} />
          <Route path="/add" element={<AddQuestion />} />
          <Route path="/:id" element={<DetailPage />} />
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<Signup />} />
          {/* path 확실하게 재수정 */}

          {/* 검색 페이지 라우팅 */}
          {/* <Route path="/search" element={<Layout />}> */}
          {/* 경로 예외처리 */}
          <Route path="*" element={<Notfound />} />
        </Route>
      </Routes>
    </>
  );
}

export default App;
