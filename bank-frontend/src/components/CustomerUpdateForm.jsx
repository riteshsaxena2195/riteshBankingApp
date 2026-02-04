import { getCustomer, deleteCustomer, updateCustomer, customerBuildDto } from '../services/api';
import { DataUpdateForm, DataFormField } from './DataForm';

const CustomerUpdateForm = ({ customerId }) => {
  return (
    <DataUpdateForm
        id={customerId}
        getFunction={getCustomer}
        deleteFunction={deleteCustomer}
        updateFunction={updateCustomer}
        buildDto={customerBuildDto}
        >
      <DataFormField label='ID' name='customerId' required disabled />
      <DataFormField label='Type' name='customerType' required disabled />
      <DataFormField label='Name' name='name' required />
      <DataFormField label='Street' name='streetNumber' required />
      <DataFormField label='City' name='city' />
      <DataFormField label='Province' name='province' />
      <DataFormField label='Postal' name='postalCode' required />
      <DataFormField label='Account IDs' name='accountIds' required disabled hidden />
    </DataUpdateForm>
  );
};

export default CustomerUpdateForm;