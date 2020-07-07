import React from 'react';
import './App.css';
import {HashRouter, BrowserRouter, Route} from 'react-router-dom'
import Login from "./pages/Login/Login"
import Register from "./pages/Register/Register";
import MainPage from "./pages/MainPage/MainPage";
function App() {
  return (
        <HashRouter>
            <Route path="/" exact component={Login}/>
            <Route path="/register" exact component={Register}/>
            <Route path="/mainpage" exact component={MainPage}/>
        </HashRouter>
  );
}

export default App;
