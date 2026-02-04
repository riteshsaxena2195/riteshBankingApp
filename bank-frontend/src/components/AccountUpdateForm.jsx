import { getAccount, deleteAccount, updateAccount, accountBuildDto } from '../services/api';
import { DataFormButton, DataFormField, DataUpdateForm } from './DataForm';
import { useNavigate } from 'react-router-dom';

const AccountUpdateForm = ({ accountId, data }) => {
  const navigate = useNavigate();

  const doViewCustomer = (data) => {
    navigate(`/customers/${data.customerId}`);
  };

  return (
    <DataUpdateForm
        id={accountId}
        getFunction={getAccount}
        deleteFunction={deleteAccount}
        updateFunction={updateAccount}
        buildDto={accountBuildDto}
        >
      <DataFormField label='ID' name='accountId' required disabled />
      <DataFormField label='Type' name='accountType' required disabled />
      <DataFormField label='Balance' name='balance' required />
      <DataFormField label='Next Check Number' name='nextCheckNumber' required conditional={data => data.accountType === 'Checking'} />
      <DataFormField label='Interest Rate' name='interestRate' required conditional={data => data.accountType === 'Savings'} />
      <DataFormField label='Customer ID' name='customerId' required disabled />
      <DataFormButton label='View Customer' doClick={doViewCustomer} />
    </DataUpdateForm>
  );
};

export default AccountUpdateForm;