import React, { useState } from "react";
import { Button, Dialog, DialogActions, DialogContent, DialogTitle, TextField } from '@mui/material';

const UpdateModal = ({open, handleClose, handleUpdate, userTin}) => {
    
    const [updateData, setUpdateData] = useState({
        updateAddress: '',
        updateEmail: '',
        updatePassword: ''
    });

    const handleChange = (e) => {
        setUpdateData((state) => ({...state, [e.target.id] : e.target.value}));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const transformedData = {
            address: updateData.updateAddress || null, 
            email: updateData.updateEmail || null, 
            password: updateData.updatePassword || null
        };
        handleUpdate(userTin, transformedData);
    }

    return (
        <Dialog open={open} onClose={handleClose}>
          <DialogTitle>Update User Details</DialogTitle>
          <DialogContent>
            <TextField
              fullWidth
              id="updateAddress"
              label="Address"
              name="updateAddress"
              value={updateData.updateAddress}
              onChange={handleChange}
              margin="normal"
            />
            <TextField
              fullWidth
              id="updateEmail"
              label="Email"
              name="updateEmail"
              value={updateData.updateEmail}
              onChange={handleChange}
              margin="normal"
            />
            <TextField
              fullWidth
              id="updatePassword"
              label="Password"
              name="updatePassword"
              value={updateData.updatePassword}
              onChange={handleChange}
              margin="normal"
            />
          </DialogContent>
          <DialogActions>
            <Button onClick={handleClose}>Cancel</Button>
            <Button onClick={handleSubmit} color="primary">Update</Button>
          </DialogActions>
        </Dialog>
      );
};

export default UpdateModal;