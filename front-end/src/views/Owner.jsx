import { useEffect, useContext } from "react";
import { useNavigate } from 'react-router-dom';
import { AuthContext } from "../contexts/AuthContext";
import UserDetails from '../components/UserDetails';
import {Box, Button} from '@mui/material';
import UserManagement from "../components/UserManagement";

const Owner = () => {
  const {authData, loading, isUser, logout} = useContext(AuthContext);
  const navigate = useNavigate();

  useEffect(() => {
    if(!loading && (!authData || !isUser(authData))){
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
      <UserDetails authData={authData} logout={logout}/>
      <UserManagement> My Profile Management </UserManagement>
    </div>
  );
};

export default Owner;
