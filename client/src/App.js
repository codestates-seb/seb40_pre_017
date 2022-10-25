import './App.scss';
import { Route, Routes } from 'react-router-dom';
import Header from './components/basic/Header';
import Main from './components/basic/Main';
import Footer from './components/basic/Footer';
import Home from './pages/Home';
import Mainpage from './pages/Mainpage';

function App() {
  // json-server --watch data.json --port 3001
  return (
    <div className='appLayout'>
      <Header />
      <Main>
        <Routes>
            {/* <Route path='/' element={<Home />} /> */}
            {/* <Route path='/' element={<Mainpage />}/> */}
        </Routes>
      </Main>
      <Footer />
    </div>
  );
}

export default App;
