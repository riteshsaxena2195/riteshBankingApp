import AccountUpdateForm from './AccountUpdateForm';
import Box from '@mui/material/Box';

import { useParams } from 'react-router-dom';

const AccountView = () => {
  const { accountId } = useParams();

  return (
    <Box>
      <AccountUpdateForm accountId={accountId}/>
    </Box>
  );
};

export default AccountView;