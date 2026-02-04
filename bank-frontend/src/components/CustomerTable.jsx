import CustomerCreateForm from './CustomerCreateForm';
import DataTable from './DataTable';
import DataTableToolbar from './DataTableToolbar';

const columns = [
  {
    field: 'customerId',
    headerName: 'ID',
    type: 'string',
    width: 60,
    editable: false,
  },
  {
    field: 'customerType',
    headerName: 'Type',
    type: 'string',
    width: 95,
    editable: false,
  },
  {
    field: 'name',
    headerName: 'Name',
    type: 'string',
    flex: true,
    editable: false,
  },
  {
    field: 'streetNumber',
    headerName: 'Street',
    type: 'string',
    flex: true,
    editable: false,
  },
  {
    field: 'city',
    headerName: 'City',
    type: 'string',
    width: 120,
    editable: false,
  },
  {
    field: 'province',
    headerName: 'State',
    type: 'string',
    width: 75,
    editable: false,
  },
  {
    field: 'postalCode',
    headerName: 'Postal',
    type: 'string',
    width: 80,
    editable: false,
  },
];

const CustomerTable = ({dataFetcher}) => {
  
  const CustomerTableToolbar = () => {
    return (
      <DataTableToolbar
          label='Customers'
          createView
          createForm={CustomerCreateForm}
          createTooltip={'Create Customer'}
          />
    );
  };

  return (
    <DataTable
      label={'Customers'}
      route={'customers'}
      columns={columns}
      dataFetcher={dataFetcher}
      rowId={(row) => row.customerId}
      dataTableToolbar={CustomerTableToolbar}
      />
  );
};

export default CustomerTable;