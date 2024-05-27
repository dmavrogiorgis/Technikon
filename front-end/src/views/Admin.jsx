import { useEffect, useContext } from "react";
import { useNavigate } from 'react-router-dom';
import { AuthContext } from "../contexts/AuthContext";
import UserDetails from '../components/UserDetails';
import UserManagement from "../components/UserManagement";
import {Box, Button} from '@mui/material';

const Admin = () => {
  const {authData, loading, isAdmin, logout} = useContext(AuthContext);
  const navigate = useNavigate();

  useEffect(() => {
    if(!loading && (!authData || !isAdmin(authData))){
      navigate('/login');
    }

  }, [authData, loading, navigate]);

  if(loading){
    return (
      <div>Loading...</div>
    );
  }

  return (
    <div>
        <Box position="absolute" top={80} right={15}>
          <Button variant="contained" color="primary" onClick={logout}>
            Logout
          </Button>
        </Box>
      <UserDetails authData={authData}/>
      <UserManagement> Admin User Management </UserManagement>    
    </div>
  );
};

export default Admin;
