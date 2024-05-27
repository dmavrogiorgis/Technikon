import React from 'react';
import { Box, Button, Container, CircularProgress, TextField, Typography } from '@mui/material';

const SearchContainer = ({ searchCriteria, handleOnChange, handleSubmit, children}) => {
  return (
    <Container maxWidth="xl">
        <Box
        component="form"
        onSubmit={handleSubmit}
        sx={{
            mt: 8,
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
        }}
        >
        <Typography variant="h4" gutterBottom>
            {children}
        </Typography>
        <Box sx={{ display: 'inline-flex', gap: '20px' }}>
            <TextField
            id="searchTin"
            name="searchTin"
            label="TIN"
            value={searchCriteria.searchTin}
            onChange={handleOnChange}
            fullWidth
            margin="normal"
            sx={{ width: '33%' }}
            />
            <TextField
            id="searchEmail"
            name="searchEmail"
            label="Email"
            value={searchCriteria.searchEmail}
            onChange={handleOnChange}
            fullWidth
            margin="normal"
            sx={{ width: '33%' }}
            />
            <TextField
            id="searchUsername"
            name="searchUsername"
            label="Username"
            value={searchCriteria.searchUsername}
            onChange={handleOnChange}
            fullWidth
            margin="normal"
            sx={{ width: '33%' }}
            />
            <Button
            type="submit"
            variant="contained"
            color="primary"
            sx={{ mt: 3, mb: 2 }}
            >
            {searchCriteria.isSubmitting ? <CircularProgress size={24} /> : 'Search'}
            </Button>
        </Box>
      </Box>
    </Container>
  );
};

export default SearchContainer;
