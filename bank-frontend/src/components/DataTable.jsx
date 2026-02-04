import * as React from 'react';
import Box from '@mui/material/Box';
import { DataGrid } from '@mui/x-data-grid';
import { useNavigate } from 'react-router-dom';

const DataTable = ({label, columns, dataFetcher, rowId, dataTableToolbar, route}) => {
  const [data, setData] = React.useState(null);
  const [error, setError] = React.useState(null);
  const navigate = useNavigate();

  React.useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await dataFetcher();
        setData(response);
      } catch (err) {
        setError(err);
      }
    };
    fetchData();
  }, [dataFetcher]);

  if (error) return <div>Error: {error.message}</div>;
  if (!data) return <div>Loading...</div>;
  
  return (
    <Box sx={{ width: '100%' }}>
      <DataGrid
        onRowClick={(params) => navigate(`/${route}/${params.id}`)}
        label={label}
        columns={columns}
        rows={data}
        getRowId={(row) => rowId(row)}
        slots={{toolbar: dataTableToolbar}}
        showToolbar
        />
    </Box>
  )
}

export default DataTable;