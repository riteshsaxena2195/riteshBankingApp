import React, { useState, useEffect } from 'react';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogTitle from '@mui/material/DialogTitle';
import FormControl from '@mui/material/FormControl';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import Select from '@mui/material/Select';
import Stack from '@mui/material/Stack';
import TextField from '@mui/material/TextField';
import { useNavigate } from 'react-router-dom';

const DataFormDropdown = ({ label, name, getState, setState, options }) => {
  const selectId = name + '-select';
  const labelId = selectId + '-label'

  return (
    <FormControl fullWidth name={name}>
      <InputLabel id={labelId}>{label}</InputLabel>
      <Select
        labelId={labelId}
        id={selectId}
        value={getState}
        label={label}
        name={name}
        onChange={(event) => setState(event.target.value)}
        fullWidth
        >
        {options.map(option => (
          <MenuItem value={option[0]}>{option[1]}</MenuItem>
        ))}
      </Select>
    </FormControl>
  );
};

const DataFormField = ({ label, type, name, defaultValue, required, disabled, hidden, conditional, data, handleInputChange }) => {
  return (
    <TextField
        label={label}
        type={type ? type : 'string'}
        name={name}
        size='small'
        fullWidth
        required={required && (conditional ? conditional(data) : true)}
        {...disabled && {variant: 'filled'}}
        {...disabled && {slotProps: {input: {readOnly: true}}}}
        {...hidden && {sx: {display: 'none'}}}
        {...conditional && !conditional(data) && {sx: {display: 'none'}}}
        defaultValue={defaultValue}
        {...handleInputChange && {onChange: handleInputChange}}
        />
  );
};

const DataFormButton = ({ label, doClick, data }) => {
  return (
    <Button sx={{ marginTop: 2 }} onClick={() => doClick(data)} variant='contained' fullWidth>
      {label}
    </Button>
  );
};

const DataCreateForm = ({ children, getId, createFunction, buildDto, location }) => {
  const navigate = useNavigate();
  
  const doCreate = async (event) => {
    event.preventDefault();
    const dto = buildDto(new FormData(event.target));
    const response = await createFunction(dto);
    navigate(`/${location}/${getId(response)}`);
    //navigate(0);
  }

  return (
    <form onSubmit={doCreate}>
      <Stack spacing={2}>
        {children}
        <Button type="submit" variant="contained" fullWidth>
          Submit
        </Button>
      </Stack>
    </form>
  );
};

const DataUpdateForm = ({ children, id, getFunction, deleteFunction, updateFunction, buildDto }) => {
  const [data, setData] = useState(null);
  const [error, setError] = useState(null);
  const [confirmDeleteOpen, setConfirmDeleteOpen] = useState(false);
  const navigate = useNavigate();

  const confirmDelete = () => {
    setConfirmDeleteOpen(true);
  };

  const closeConfirmDelete = (value) => {
    setConfirmDeleteOpen(false);
  };

  const doDelete = async () => {
    await deleteFunction(id);
    navigate(-1);
  }

  const doUpdate = async (event) => {
    event.preventDefault();
    const dto = buildDto(new FormData(event.target));
    await updateFunction(id, dto);
    setIsFormChanged(false);
    navigate(0);
  }

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await getFunction(id);
        setData(response);
      } catch (error) {
        setError(error);
      }
    };
    fetchData();
  }, [id, getFunction]);

  const initialFormState = data;

  const [formState, setFormState] = useState(initialFormState);
  const [isFormChanged, setIsFormChanged] = useState(false);

  useEffect(() => {
    setIsFormChanged(JSON.stringify(formState) !== JSON.stringify(initialFormState));
  }, [formState]);

  const handleIChange = (event) => {
    setFormState({ ...formState, [event.target.name]: event.target.value });
  };

  if (error) return <div>Error: {error.message}</div>;
  if (!data) return <div>Loading...</div>;

  const childrenWithData = React.Children.map(children, child => {
    if (React.isValidElement(child)) {
      return React.cloneElement(child, { handleInputChange: handleIChange, data: data, defaultValue: data[child.props.name] })
    }
    return child;
  });

  return (
    <form onSubmit={doUpdate}>
      <Stack spacing={2}>
        {childrenWithData}
        <Stack direction='row' spacing={2}>
          <Button type='submit' color='success' variant='contained' fullWidth disabled={!isFormChanged}>
            Save
          </Button>
          <Button onClick={confirmDelete} color='error' variant='contained' fullWidth>
            Delete
          </Button>
        </Stack>
        <div>
          <Dialog open={confirmDeleteOpen} onClose={closeConfirmDelete}>
            <DialogTitle>Confirm Deletion?</DialogTitle>
            <DialogActions sx={{ margin: 2 }}>
              <Button variant='contained' onClick={closeConfirmDelete}>Cancel</Button>
              <Button variant='contained' onClick={doDelete} color="error">
                Delete
              </Button>
            </DialogActions>
          </Dialog>
        </div>
      </Stack>
    </form>
  );
};

export { DataCreateForm, DataUpdateForm, DataFormButton, DataFormDropdown, DataFormField };