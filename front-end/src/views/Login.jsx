import React, {useState, useContext} from "react";
import {Avatar, TextField, Button, Box, Link, Typography, Container, CircularProgress } from '@mui/material';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import { useNavigate } from 'react-router-dom';
import { AuthContext } from '../contexts/AuthContext';

const Login = () => {

    const [loginData, setLoginData] = useState({
        loginUsername: "",
        loginPassword: "",
        isSubmitting: false,
        error: null,
    });

    const { login } = useContext(AuthContext);

    const navigate = useNavigate();

    const updateLoginForm = (e) => {
        setLoginData((state) => ({...state, [e.target.id] : e.target.value}));
    };

    const submitLogin =  async (e) => {
        e.preventDefault();
        setLoginData({...loginData, isSubmitting: true, error: null});
        
        const transformedData = {
            username: loginData.loginUsername, 
            password: loginData.loginPassword
        };

        const result = await login(transformedData);
        console.log('authdata:', JSON.parse(localStorage.getItem('authData')));
        if(result.success){
          if(result.isAdmin){
            navigate('/admin');
          }else{
              navigate('/owner');
          }
        }else{
          setLoginData({...loginData, isSubmitting: false, error: result.error})
        }
        
        
    };

    return (
        <Container component="main" maxWidth="xs">
          <Box 
            component="form" 
            onSubmit={submitLogin}  
            sx={{ 
              mt: 8, 
              display: 'flex',
              flexDirection: 'column',
              alignItems: 'center', 
            }}
          >
          <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
            <LockOutlinedIcon />
          </Avatar>
          <Typography component="h1" variant="h5">
            Sign In
          </Typography>
          <TextField
            variant="outlined"
            margin="normal"
            required
            fullWidth
            id="loginUsername"
            label="Username"
            name="loginUsername"
            autoComplete="username"
            autoFocus
            value={loginData.loginUsername}
            onChange={updateLoginForm}
          />
          <TextField
            variant="outlined"
            margin="normal"
            required
            fullWidth
            name="loginPassword"
            label="Password"
            type="password"
            id="loginPassword"
            autoComplete="current-password"
            value={loginData.loginPassword}
            onChange={updateLoginForm}
          />
            {loginData.error && (
              <Typography color="error" variant="body2">
                {loginData.error}
              </Typography>
            )}
          <Button
            type="submit"
            variant="contained"
            color="primary"
            sx={{ mt: 3, mb: 2 }}
          > 
            {loginData.isSubmitting ? <CircularProgress size={24} /> : "Sign In"} 
          </Button>
          <Box sx={{ alignSelf: "flex-end", mt: 2 }}>
            <Link href="/signup" variant="body2">
              Don't have an acount? Sign Up
            </Link>
          </Box>
        </Box>
    </Container>
    );
}

export default Login;