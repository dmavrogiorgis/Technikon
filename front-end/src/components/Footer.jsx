import {AppBar, Box, Toolbar, Typography, Link} from "@mui/material";

const Footer = () => {
  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static" color="primary" sx={{ top: 'auto', bottom: 0 }}>
        <Toolbar>
          <Typography variant="body1" color="inherit" sx={{ flexGrow: 1, textAlign: 'center' }}>
            © 2024 Technikon Team 3
          </Typography>
        </Toolbar>
      </AppBar>
    </Box>
  );
};

export default Footer;
