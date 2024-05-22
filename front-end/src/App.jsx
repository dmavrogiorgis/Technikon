import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import MainLayout from "./layouts/MainLayout";
import Home from "./views/Home/Home";
import Admin from "./views/Admin";
import Owner from "./views/Owner";
import Login from "./views/Login";
function App() {
  return (
    <Router>
      <Routes>
        <Route element={<MainLayout/>}>
          <Route path="/" element={<Home/>}/>
          <Route path="/admin" element={<Admin/>}/>
          <Route path="/owner" element={<Owner/>}/>
          <Route path="/login" element={<Login />}/>
        </Route>
      </Routes>
    </Router>
  );
}

export default App;
