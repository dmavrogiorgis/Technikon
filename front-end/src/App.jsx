import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import MainLayout from "./layouts/MainLayout";
import Home from "./views/Home/Home";
import Admin from "./views/Admin";
import Owner from "./views/Owner";
import Login from "./views/Login";
import Signup from "./views/Signup";
import Repair from "./views/Repair";
import PropertiesListContainer from "./PropertiesListContainer"
import { AuthProvider } from './contexts/AuthContext';

function App() {
  return (
    <AuthProvider>
      <Router>
      <Routes>
        <Route element={<MainLayout/>}>
          <Route path="/" element={<Home/>}/>
          <Route path="/admin" element={<Admin/>}/>
          <Route path="/owner" element={<Owner/>}/>
          <Route path="/properties" element={<PropertiesListContainer />}/>
          <Route path="/repairs" element={<Repair />}/>
          <Route path="/login" element={<Login />}/>
          <Route path="/signup" element={<Signup />}/>
        </Route>
      </Routes>
    </Router>
    </AuthProvider>
    
  );
}

export default App;
