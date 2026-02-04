import AccountTable from './AccountTable';
import Box from '@mui/material/Box';
import CustomerUpdateForm from './CustomerUpdateForm';

import { getCustomerAccounts } from '../services/api';
import { useParams } from 'react-router-dom';

const CustomerView = () => {
  const { customerId } = useParams();

  return (
    <Box>
      <CustomerUpdateForm customerId={customerId}/>
      <Box sx={{mt:2}}>
        <AccountTable getCustomerId={() => customerId} dataFetcher={() => getCustomerAccounts(customerId)} />
      </Box>
    </Box>
  );
};

export default CustomerView;