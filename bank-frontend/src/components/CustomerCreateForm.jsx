import { useState } from 'react';
import { createCustomer, customerBuildDto } from '../services/api';
import { DataCreateForm, DataFormDropdown, DataFormField } from './DataForm';

const CustomerCreateForm = () => {
  const [ customerType, setCustomerType ] = useState('Person');

  return (
    <DataCreateForm
        getId={response => response.customerId}
        createFunction={createCustomer}
        buildDto={customerBuildDto}
        location='customers'
        >
      <DataFormDropdown
          label='Customer Type'
          name='customerType'
          getState={customerType}
          setState={setCustomerType}
          options={[['Person', 'Person'], ['Company', 'Company']]}
          />
      <DataFormField label='Name' name='name' required />
      <DataFormField label='Street' name='streetNumber' required />
      <DataFormField label='City' name='city' />
      <DataFormField label='Province' name='province' />
      <DataFormField label='Postal' name='postalCode' required />
    </DataCreateForm>
  );
};

export default CustomerCreateForm;