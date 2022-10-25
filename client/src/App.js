import './App.scss';
import { Route, Routes } from 'react-router-dom';
import Home from './pages/Home';
import Layout from './components/js/basic/Layout';
import Notfound from './components/js/basic/Notfound';
// import Mainpage from './pages/Mainpage';

function App() {
  // json-server --watch data.json --port 3001
  return (
    <>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<Home />} />
        </Route>
        <Route path="/questions" element={<Layout />}>
          {/* 질문 페이지 라우팅 */}
          {/* <Route index element={<Home />} /> */}
        </Route>
        <Route path="/search" element={<Layout />}>
          {/* 검색 페이지 라우팅 */}
          {/* <Route index element={<Home />} /> */}
        </Route>
        <Route path="*" element={<Notfound />} />
          {/* 경로 예외처리 */}
      </Routes>
    </>
  );
}

export default App;
