import {AppBar, Box, Toolbar, Typography, Link} from "@mui/material";

const Footer = () => {
  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static" color="primary" sx={{ top: 'auto', bottom: 0 }}>
        <Toolbar>
          <Typography variant="body1" component="div" sx={{ flexGrow: 1 }}>
            <Link href="/about" color="inherit">
              About Us
            </Link>
          </Typography>
          <Typography variant="body1" component="div" sx={{ flexGrow: 1 }}>
            <Link href="/contact" color="inherit">
              Contact Us
            </Link>
          </Typography>
          <Typography variant="body1" component="div" sx={{ flexGrow: 1 }}>
            <Link href="/privacy" color="inherit">
              Privacy Policy
            </Link>
          </Typography>
        </Toolbar>
      </AppBar>
    </Box>
  );
};

export default Footer;
