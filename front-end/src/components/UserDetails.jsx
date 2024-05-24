import React from 'react';
import { Typography, Container, Paper, Grid, Box} from '@mui/material';

const UserDetails = ({authData}) => {
  return (
    <Box position="relative">
      <Container maxWidth="md">
        <Typography 
          variant="h4" 
          gutterBottom 
          sx={{ 
              mt: 8, 
              display: 'flex',
              flexDirection: 'column',
              alignItems: 'center'}}
        >
          User Profile
        </Typography>
          {authData && (
          <Paper elevation={3} sx={{ padding: 2 }}>
            <Grid container spacing={3}>
              <Grid item xs={12}>
                <Typography variant="body1" gutterBottom>
                  <strong>Username:</strong> {authData.username}
                </Typography>
              </Grid>
              <Grid item xs={12}>
                <Typography variant="body1" gutterBottom>
                  <strong>Email:</strong> {authData.email}
                </Typography>
              </Grid>
              <Grid item xs={12}>
                <Typography variant="body1" gutterBottom>
                  <strong>TIN:</strong> {authData.tin}
                </Typography>
              </Grid>
              <Grid item xs={12}>
                <Typography variant="body1" gutterBottom>
                  <strong>Roles:</strong> {authData.authorities.map(auth => auth.authority).join(', ')}
                </Typography>
              </Grid>
            </Grid>
          </Paper>
          )}
      </Container>
    </Box>
  );
  
};


export default UserDetails;
