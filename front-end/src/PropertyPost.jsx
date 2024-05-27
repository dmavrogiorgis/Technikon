import React, { useState } from "react";
import { TextField, Button, Box, Typography, Container, Alert, FormControl,InputLabel, Select, MenuItem } from '@mui/material';
import axios from 'axios';
import { API_LOGIN } from '/src/api';
import { useNavigate } from 'react-router-dom';

const PropertyPost = () => {

    const [propData, setPropData] = useState({
        propId: "0",
        propIn: "",
        propAddress: "",
        propYearOfCon: "",
        propImagePath: "",
        propType: "",
        propOwnerId: "3",
    });

    const navigate = useNavigate();

    const updatePropertyForm = (e) => {
        setPropData((state) => ({ ...state, [e.target.id]: e.target.value }));
    };

    const submitProperty = async (e) => {
        e.preventDefault();

        const transformedData = {
            In: propData.propIn,
            address: propData.propAddress,
            constructionYear: propData.propYearOfCon,
            imgPath: propData.propImagePath,
            type: propData.propType,
            ownerId: propData.propOwnerId
        };

        try {
            const response = await axios.post('http://localhost:8080/api/owner/1/property', transformedData);

            if (response.status === 200) {

                alert('Property created properly');
            }
        } catch (error) {
            console.error('Error logging in:', error);
        }
    };

    return (
        <Container component="main" maxWidth="xs">
            <Box
                component="form"
                onSubmit={submitProperty}
                sx={{
                    mt: 10,
                    display: 'flex',
                    flexDirection: 'column',
                    alignItems: 'center',
                }}>
                <Typography component="h1" variant="h5">
                    Add Property
                </Typography>
                <TextField fullWidth
                    variant="outlined"
                    margin="normal"
                    required
                    id="propIn"
                    label="Identification number"
                    name="propIn"
                    autoFocus
                    target={propData.propIn}
                    onChange={updatePropertyForm}
                />
                <TextField fullWidth
                    variant="outlined"
                    margin="normal"
                    required
                    id="propAddress"
                    label="Address"
                    name="propAddress"
                    autoFocus
                    target={propData.propAddress}
                    onChange={updatePropertyForm}
                />
                <TextField fullWidth
                    variant="outlined"
                    margin="normal"
                    required
                    id="propYearOfCon"
                    label="ConstructionYear"
                    name="propYearOfCon"
                    autoFocus
                    target={propData.propYearOfCon}
                    onChange={updatePropertyForm}
                />
                <FormControl fullWidth>
                    <InputLabel >Property Type</InputLabel>
                    <Select
                        id="propType"
                        label="propType"
                        target={propData.propType}
                        onChange={updatePropertyForm}
                        >

                        <MenuItem value={'DETACHED_HOUSE'}>Detached House</MenuItem>
                        <MenuItem value={'MAISONETTE'}>Maisonette</MenuItem>
                        <MenuItem value={'APARTMENT'}>Apartment</MenuItem>
                    </Select>
                </FormControl>
                <Button
                    type="submit"
                    variant="contained"
                    color="primary"
                    sx={{ mt: 3, mb: 2 }}
                //disabled={isLoading}
                > Register 
                </Button>
            </Box>
        </Container>
    );
}

export default PropertyPost;