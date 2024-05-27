import { Typography, Button, Container, Box, Grid, Paper } from '@mui/material';


const Home = () => {
  return (
    <>
    <Box

    >
      <Typography variant="h3" component="h1" gutterBottom align="center">
        Welcome to Technikon!
      </Typography>
    </Box>

    <Container sx={{ py: 6 }}>
      <Typography variant="h4" component="h2" gutterBottom align="center">
        Key Features
      </Typography>
      <Grid container spacing={4}>
        <Grid item xs={12} md={4}>
          <Paper sx={{ p: 3, textAlign: 'center' }}>
            <Typography variant="h6" component="h3">Property Owners</Typography>
            <Typography>Manage and view all property owner details and their associated properties.</Typography>
          </Paper>
        </Grid>
        <Grid item xs={12} md={4}>
          <Paper sx={{ p: 3, textAlign: 'center' }}>
            <Typography variant="h6" component="h3">Properties</Typography>
            <Typography>Access comprehensive information about all managed properties, including location, status, and maintenance history.</Typography>
          </Paper>
        </Grid>
        <Grid item xs={12} md={4}>
          <Paper sx={{ p: 3, textAlign: 'center' }}>
            <Typography variant="h6" component="h3">Repairs</Typography>
            <Typography>Track, schedule, and update repair tasks and maintenance work for all properties.</Typography>
          </Paper>
        </Grid>
      </Grid>
    </Container>
    </>
  );
};

export default Home;
