import AppBar from '@mui/material/AppBar';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import MenuIcon from '@mui/icons-material/Menu';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import { useNavigate } from 'react-router-dom';

export const NavigationBarLink = ({ primary, onClick }) => {
  return (
    <Button color='inherit' onClick={onClick}>
      {primary}
    </Button>
  );
}

export const NavigationBar = ({ children, primary, handleDrawerToggle }) => {
  const navigate = useNavigate();

  return (
    <AppBar sx={{ zIndex: 1400 }}>
      <Toolbar>
        <IconButton color="inherit" aria-label="open drawer" edge="start" onClick={handleDrawerToggle} sx={{ mr: 2, display: { md: 'none' } }}>
          <MenuIcon />
        </IconButton>
        <Button onClick={() => navigate('/')} color='inherit' sx={{ marginRight: 'auto' }}>
          <Typography variant='h6' noWrap component='div'>
            {primary}
          </Typography>
        </Button>
        {children}
      </Toolbar>
    </AppBar>
  );
};