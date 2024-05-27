import React, {useState} from "react";
import { TextField, Button, Box, Typography, Container} from '@mui/material';
import axios from 'axios';
import Avatar from '@mui/material/Avatar';
import CssBaseline from '@mui/material/CssBaseline';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import { API_SIGNUP } from '../api';
import { useNavigate } from "react-router-dom";
import * as Yup from "yup";


const SignInSchema = Yup.object().shape({
    email: Yup.string()
      .email('Email is not valid.')
      .required('Email is required.'),
    password: Yup.string()
      .required('Password is required.')
      .min(8, 'Password length should be at least 8 characters.'),
    fullname: Yup.string()
      .required('Full name is required.')
      .min(4, 'Name should be at least 4 characters.')
  });
const Signup = () => {

    const [signupData, setSignupData] = useState({
        signupFirstname:"",
        signupLastname:"",
        signupAddress:"",
        signupEmail:"",
        signupPhoneNumber:"",
        signupTin:"",
        signupUsername:"",
        signupPassword:""
    });
    
    const navigate = useNavigate();

    const updateSignupForm = (e) => {
        setSignupData((state) => ({...state, [e.target.id] : e.target.value}));

    };

    const submitSignup =  async (e) => {
        e.preventDefault();
        
        const transformedData = {
            tin : signupData.signupTin,
            name: signupData.signupFirstname,
            surname : signupData.signupLastname,
            address : signupData.signupAddress,
            phoneNumber : signupData.signupPhoneNumber,
            email: signupData.signupEmail,            
            username : signupData.signupUsername,
            password : signupData.signupPassword, 
        };

        try{
            const response = await axios.post(API_SIGNUP, transformedData);
            navigate('/login');
        } catch (error) {
            console.error('Error while signing up:', error);
        }
    };
    return(
    <Container component="main" maxWidth="xs">
        <CssBaseline />
        <Box
            sx={{
                marginTop: 8,
                display: 'flex',
                flexDirection: 'column',
                alignItems: 'center',
            }}
        >
            <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
                <LockOutlinedIcon />
            </Avatar>
            <Typography component="h1" variant="h5">
                Sign up
            </Typography>
            <Box component="form"
                            onSubmit={submitSignup} sx={{ mt: 3 }}>
                <Grid container spacing={2}>
                    <Grid item xs={12} sm={6}>
                        <TextField
                            name="signupFirstname"
                            required
                            fullWidth
                            id="signupFirstname"
                            label="First Name"
                            target={signupData.signupFirstname}
                            onChange={updateSignupForm}
                        />
                    </Grid>
                    <Grid item xs={12} sm={6}>
                        <TextField
                            required
                            fullWidth
                            id="signupLastname"
                            label="Last Name"
                            name="signupLastname"
                            target={signupData.signupLastname}
                            onChange={updateSignupForm}
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <TextField
                            fullWidth
                            name="signupPhoneNumber"
                            label="Phone Number"
                            type="Phone Number"
                            id="signupPhoneNumber"
                            target={signupData.signupPhoneNumber}
                            onChange={updateSignupForm}
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <TextField
                            required
                            fullWidth
                            id="signupEmail"
                            label="Email Address"
                            name="signupEmail"
                            target={signupData.signupEmail}
                            onChange={updateSignupForm}
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <TextField
                            required
                            fullWidth
                            id="signupAddress"
                            label="Address"
                            type="address"
                            name="signupAddress"
                            target={signupData.signupAddress}
                            onChange={updateSignupForm}
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <TextField
                            required
                            fullWidth
                            name="signupTin"
                            label="TIN Number"
                            type="TIN Number"
                            id="signupTin"
                            target={signupData.signupTin}
                            onChange={updateSignupForm}
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <TextField
                            required
                            fullWidth
                            id="signupUsername"
                            label="Username"
                            name="signupUsername"
                            target={signupData.signupUsername}
                            onChange={updateSignupForm}
                        />
                    </Grid>
                    <Grid item xs={12}>
                        <TextField
                            required
                            fullWidth
                            name="signupPassword"
                            label="Password"
                            type="password"
                            id="signupPassword"
                            target={signupData.signupPassword}
                            onChange={updateSignupForm}
                        />
                    </Grid>
                </Grid>
                <Button
                    type="submit"
                    fullWidth
                    variant="contained"
                    sx={{ mt: 3, mb: 2 }}
                >  Sign Up
                </Button>
                <Grid container justifyContent="flex-end">
                    <Grid item>
                        <Link href="/login" variant="body2">
                            Already have an account? Sign in
                        </Link>
                    </Grid>
                </Grid>
            </Box>
        </Box>

    </Container>
    );}
export default Signup;