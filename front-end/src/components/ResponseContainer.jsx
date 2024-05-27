import React from 'react';
import { Container, Box, Button, Typography } from '@mui/material';

const ResponseContainer = ({ response, handleUpdate, handleDelete }) => {
  return (
    <Container>
      {response.success && response.data ? (
        <>
          {Object.entries(response.data).map(([key, value]) => (
            <Box key={key} display="flex" alignItems="center" mt={2}>
              <Typography variant="body1">
                <strong>{key}:</strong> {key === 'active' ? (value ? 'ACTIVE' : 'INACTIVE') : value}
              </Typography>
            </Box>
          ))}
          <Box display="flex" justifyContent="flex-start" mt={1} mb={2} >
            <Button
              onClick={handleUpdate}
              variant="contained"
              color="primary"
              size="small"
              style={{ marginRight: '8px' }}
            >
              Update
            </Button>
            <Button
              onClick={handleDelete}
              variant="contained"
              color="secondary"
              size="small"
            >
              Delete
            </Button>
          </Box>
        </>
      ) : (
        <Typography variant="h6" color="error" sx={{ marginTop: 2 }}>
          {response.error}
        </Typography>
      )}
    </Container>
  );
};

export default ResponseContainer;
