import './App.scss';
import { Route, Routes } from 'react-router-dom';
import Header from './components/js/basic/Header';
import Main from './components/js/basic/Main';
import Footer from './components/js/basic/Footer';
import Home from './pages/Home';
// import Mainpage from './pages/Mainpage';

function App() {
  // json-server --watch data.json --port 3001
  return (
    <div className='appLayout'>
      <Header />

      <Routes>
        <Route path="/" element={<Main />}>
          <Route index element={<Home />} />
        </Route>
      </Routes>
      
      <Footer />
    </div>
  );
}

export default App;
