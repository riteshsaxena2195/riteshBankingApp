import AccountCreateForm, { setCustomerId } from './AccountCreateForm';
import DataTable from './DataTable';
import DataTableToolbar from './DataTableToolbar';

const columns = [
  {
    field: 'accountId',
    headerName: 'ID',
    type: 'string',
    width: 60,
    editable: false,
  },
  {
    field: 'accountType',
    headerName: 'Type',
    type: 'string',
    width: 100,
    editable: false,
  },
  {
    field: 'balance',
    headerName: 'Balance',
    type: 'number',
    flex: true,
    editable: false,
    valueFormatter: (value) => parseFloat(value.toFixed(2)).toLocaleString(),
  },
  {
    field: 'nextCheckNumber',
    headerName: 'Check',
    type: 'number',
    width: 100,
    editable: false,
    valueFormatter: (value) => {return value !== 0 ? value : ''},
  },
  {
    field: 'interestRate',
    headerName: 'Interest',
    type: 'number',
    width: 100,
    editable: false,
    valueGetter: (value) => value * 100,
    valueFormatter: (value) => {return (!Number.isNaN(value) && value !== 0) ? `${value.toFixed(2)}%` : ''},
  },
  {
    field: 'customerId',
    headerName: 'Customer ID',
    type: 'string',
    width: 100,
    align: 'right',
    editable: false,
  },
];

const AccountTable = ({ dataFetcher, getCustomerId }) => {
  if (getCustomerId)
    setCustomerId(getCustomerId());

  const AccountTableToolbar = () => {
    return (
      <DataTableToolbar
          label='Accounts'
          createView={getCustomerId}
          createForm={AccountCreateForm}
          createTooltip={'Create Account'}
          />
    );
  };

  return (
    <DataTable
      label={'Accounts'}
      route={'accounts'}
      columns={columns}
      dataFetcher={dataFetcher}
      rowId={(row) => row.accountId}
      dataTableToolbar={AccountTableToolbar}
      />
  );
};

export default AccountTable;