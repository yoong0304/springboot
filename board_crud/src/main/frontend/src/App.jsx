// #frontend/src/App.jsx
import {Route, Routes} from 'react-router-dom';
import { BrowserRouter } from 'react-router-dom';
import Main from './pages/Main';
import Detail from './pages/Detail';
import CreateBoard from './pages/CreateBoard';
import UpdateBoard from './pages/UpdateBoard';



function App() {
  return (
      <>
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<Main />} />
            {/* 경로 "/"에 대한 Main 컴포넌트를 매핑 합니다. */}
            <Route path="/detail" element={<Detail />} />
            <Route path="/create-board" element={<CreateBoard />} />
            <Route path="/update-board" element={<UpdateBoard />} />
          </Routes>
        </BrowserRouter>
      </>
  );
}
export default App;