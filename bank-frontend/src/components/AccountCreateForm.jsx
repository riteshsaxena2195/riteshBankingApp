import { useState } from 'react';
import { createAccount, accountBuildDto } from '../services/api';
import { DataCreateForm, DataFormDropdown, DataFormField } from './DataForm';

var customerId = 0;

export const setCustomerId = (id) => {
  customerId = id;
};

const AccountCreateForm = () => {
  const [accountType, setAccountType] = useState('Checking');

  return (
    <DataCreateForm
        getId={() => customerId}
        createFunction={createAccount}
        buildDto={accountBuildDto}
        location='customers'
        >
      <DataFormDropdown
          label='Account Type'
          name='accountType'
          getState={accountType}
          setState={setAccountType}
          options={[['Checking', 'Checking'], ['Savings', 'Savings']]}
          />
      <DataFormField label='Balance' type='number' name='balance' required />
      <DataFormField label='Next Check Number' type='number' name='nextCheckNumber' required conditional={() => accountType === 'Checking'} />
      <DataFormField label='Interest Rate' type='number' name='interestRate' required conditional={() => accountType === 'Savings'} />
      <DataFormField label='Customer ID' name='customerId' required disabled defaultValue={customerId} />
    </DataCreateForm>
  );
};

export default AccountCreateForm;