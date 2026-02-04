import Box from '@mui/material/Box';
import AccountTable from './components/AccountTable';
import AccountView from './components/AccountView';
import Container from '@mui/material/Container';
import ContentHeader from './components/ContentHeader';
import CssBaseline from '@mui/material/CssBaseline';
import CustomerTable from './components/CustomerTable';
import CustomerView from './components/CustomerView';
import Divider from '@mui/material/Divider';
import Toolbar from '@mui/material/Toolbar';
import { NavigationDrawer, NavigationDrawerLink, NavigationDrawerForm } from './components/NavigationDrawer';
import { NavigationBar, NavigationBarLink } from './components/NavigationBar';

import { Routes, Route, useNavigate } from 'react-router-dom';
import { useState } from 'react';
import { getCustomers, getAccounts } from './services/api';

const drawerWidth = 240;

const PageHome = () => (
  <div>
    <p>Home page</p>
  </div>
);

const PageAccounts = () => (
  <div>
    <ContentHeader primary='Viewing All Accounts' />
    <AccountTable dataFetcher={() => getAccounts()} />
  </div>
);

const PageAccount = () => (
  <div>
    <ContentHeader primary='Viewing Account' />
    <AccountView />
  </div>
);

const PageCustomers = () => (
  <div>
    <ContentHeader primary='Viewing All Customers' />
    <CustomerTable dataFetcher={() => getCustomers()} />
  </div>
);

const PageCustomer = () => (
  <div>
    <ContentHeader primary='Viewing Customer' />
    <CustomerView />
  </div>
);

export default function App() {
  const navigate = useNavigate();
  const refresh = (location) => { navigate(location); navigate(0); }
  const [mobileOpen, setMobileOpen] = useState(false);

  const isMobileOpen = () => mobileOpen;
  
  const handleDrawerToggle = () => {
    setMobileOpen(!isMobileOpen());
  };

  const handleDrawerClose = () => {
    setMobileOpen(false);
  };

  return (
    <Container disableGutters className='app'>
      <CssBaseline />
      <Box sx={{ display: 'flex' }}>
        <NavigationBar handleDrawerToggle={handleDrawerToggle} primary='FDM Bank'>
          <NavigationBarLink primary='Log In'/>
          <NavigationBarLink primary='Register'/>
        </NavigationBar>
        <NavigationDrawer handleDrawerClose={handleDrawerClose} isMobileOpen={isMobileOpen} drawerWidth={drawerWidth}>
          <NavigationDrawerLink primary='Accounts' onClick={() => navigate('/accounts')} />
          <NavigationDrawerForm primary='Account ID' name='findAccount' onSubmit={(id) => refresh(`/accounts/${id}`)} />
          <Divider />
          <NavigationDrawerLink primary='Customers' onClick={() => navigate('/customers')} />
          <NavigationDrawerForm primary='Customer ID' name='findCustomer' onSubmit={(id) => refresh(`/customers/${id}`)} />
        </NavigationDrawer>
        <Box component="main" sx={{ flexGrow: 1, p: 3, width: { md: `calc(100% - ${drawerWidth}px)` } }}>
          <Toolbar />
          <Routes>
            <Route path='' element={<PageHome />}/>
            <Route path='accounts' element={<PageAccounts />}/>
            <Route path='accounts/:accountId' element={<PageAccount />}/>
            <Route path='customers' element={<PageCustomers />}/>
            <Route path='customers/:customerId' element={<PageCustomer />}/>
          </Routes>
        </Box>
      </Box>
    </Container>
  );
};