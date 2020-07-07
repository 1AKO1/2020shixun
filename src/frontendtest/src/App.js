import React from 'react';
import './App.css';
import { BrowserRouter as Router, Route} from 'react-router-dom'
import Login from './pages/login/Login'
import Register from "./pages/Register/Register";
import Welcome from "./pages/Welcome/Welcome";
import MainPage from "./pages/MainPage/MainPage";
function App() {
  return (
      <Router>
          <Route path="/" exact component={Welcome}/>
          <Route path="/login" component={Login} />
          <Route path="/register" component={Register}/>
          <Route path="/mainPage" component={MainPage}/>
      </Router>
  );
}

export default App;
