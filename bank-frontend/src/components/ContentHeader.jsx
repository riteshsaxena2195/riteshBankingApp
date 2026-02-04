import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import IconButton from '@mui/material/IconButton';
import Stack from '@mui/material/Stack';
import Tooltip from '@mui/material/Tooltip';
import Typography from '@mui/material/Typography';
import { useNavigate } from 'react-router-dom';

const ContentHeader = ({ primary }) => {
  const navigate = useNavigate();

  return (
    <Stack direction='row' sx={{ width: '100%', paddingBottom: 1 }} alignItems='center'>
      <Tooltip title='Go Back' sx={{ marginRight: 2 }}>
        <IconButton onClick={() => {navigate(-1)}}>
          <ArrowBackIcon fontSize="small" />
        </IconButton>
      </Tooltip>
      <Typography variant='body1' sx={{ justifySelf: 'center' }}>
        {primary}
      </Typography>
    </Stack>
  );
};

export default ContentHeader;