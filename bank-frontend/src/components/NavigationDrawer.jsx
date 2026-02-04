import React from 'react';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Drawer from '@mui/material/Drawer';
import Stack from '@mui/material/Stack';
import TextField from '@mui/material/TextField';
import Toolbar from '@mui/material/Toolbar';

export const NavigationDrawerLink = ({ primary, onClick, doClose }) => {
  const handleClick = () => {
    doClose();
    onClick();
  };

  return (
    <Button onClick={handleClick} color='inherit' fullWidth style={{justifyContent: "flex-start"}}>
      {primary}
    </Button>
  );
};

export const NavigationDrawerForm = ({ primary, name, onSubmit, doClose }) => {
  const handleSubmit = (event) => {
    event.preventDefault();
    const formData = new FormData(event.target);
    doClose();
    onSubmit(formData.get(name));
  };

  return (
    <form onSubmit={handleSubmit}>
      <Stack direction='row'>
        <Button type='submit' color='inherit' style={{justifyContent: "flex-start"}}>
          Find
        </Button>
        <TextField sx={{ width: 140 }} label={primary} type='number' name={name} size='small' required />
      </Stack>
    </form>
  );
};

export const NavigationDrawer = ({ children, drawerWidth, isMobileOpen, handleDrawerClose }) => {

  const NavigationDrawerPermanent = ({ children }) => {
    return (
      <Drawer variant='permanent' open='true' sx={{display: { xs: 'none', md: 'block' }}}
          slotProps={{ paper: { sx: { width: `${drawerWidth}px`} } }}>
        {children}
      </Drawer>
    );
  };

  const NavigationDrawerTemporary = ({ children }) => {
    return (
      <Drawer
        variant='temporary'
        open={isMobileOpen()}
        onClose={handleDrawerClose}
        sx={{
          display: { xs: 'block', md: 'none' },
          '& .MuiDrawer-paper': { boxSizing: 'border-box', width: `${drawerWidth}px` },
        }}
        slotProps={{
          root: {
            keepMounted: true,
          },
        }}
      >
        {children}
      </Drawer>
    );
  };

  const childrenWithProps = React.Children.map(children, child => {
    if (React.isValidElement(child))
      return React.cloneElement(child, { doClose: handleDrawerClose })
    return child;
  });

  return (
    <Box component="nav" sx={{ zIndex: 0, width: { md: `${drawerWidth}px` }, flexShrink: { md: 0 } }} aria-label="mailbox folders">
      <NavigationDrawerPermanent>
        <Toolbar />
        <Stack gap={2} sx={{px: 2, my: 2}}>
          {childrenWithProps}
        </Stack>
      </NavigationDrawerPermanent>
      <NavigationDrawerTemporary>
        <Toolbar />
        <Stack gap={2} sx={{px: 2, my: 2}}>
          {childrenWithProps}
        </Stack>
      </NavigationDrawerTemporary>
    </Box>
  );
};