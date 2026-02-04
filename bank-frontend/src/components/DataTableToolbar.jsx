import * as React from 'react';
import AddIcon from '@mui/icons-material/Add';
import Badge from '@mui/material/Badge';
import ClickAwayListener from '@mui/material/ClickAwayListener';
import FilterListIcon from '@mui/icons-material/FilterList';
import Paper from '@mui/material/Paper';
import Popper from '@mui/material/Popper';
import Tooltip from '@mui/material/Tooltip';
import Typography from '@mui/material/Typography';
import ViewColumnIcon from '@mui/icons-material/ViewColumn';
import { Toolbar, ToolbarButton, ColumnsPanelTrigger, FilterPanelTrigger } from '@mui/x-data-grid';

const DataTableToolbarCreateView = ({ tooltip, createForm }) => {
  const [newPanelOpen, setNewPanelOpen] = React.useState(false);
  const newPanelTriggerRef = React.useRef(null);

  const handleClose = () => {
    setNewPanelOpen(false);
  };

  const handleKeyDown = (event) => {
    if (event.key === 'Escape') {
      handleClose();
    }
  };

  return (
    <div>
      <Tooltip title={tooltip}>
        <ToolbarButton
          ref={newPanelTriggerRef}
          aria-describedby="new-panel"
          onClick={() => setNewPanelOpen((prev) => !prev)}
        >
          <AddIcon fontSize="small" />
        </ToolbarButton>
      </Tooltip>

      <Popper
        open={newPanelOpen}
        anchorEl={newPanelTriggerRef.current}
        placement="bottom-end"
        id="new-panel"
        onKeyDown={handleKeyDown}
      >
        <ClickAwayListener onClickAway={(event) => {
          //console.log('clicked away');
          //handleClose();
        }}>
          <Paper sx={{ display: 'flex', flexDirection: 'column', gap: 2, width: 300, p: 2, }} elevation={8}>
            <Typography fontWeight="bold">{tooltip}</Typography>
            {createForm()}
          </Paper>
        </ClickAwayListener>
      </Popper>
    </div>
  );
};

const DataTableToolbar = ({ children, label, createView, createForm, createTooltip }) => {
  return (
    <Toolbar>
      <Typography fontWeight="medium" sx={{ flex: 1, mx: 0.5 }}>
        {label}
      </Typography>
      {createView && (
        <DataTableToolbarCreateView createForm={createForm} tooltip={createTooltip} />
      )}
      {children}
      <Tooltip title="Columns">
        <ColumnsPanelTrigger render={<ToolbarButton />}>
          <ViewColumnIcon fontSize="small" />
        </ColumnsPanelTrigger>
      </Tooltip>
      <Tooltip title="Filters">
        <FilterPanelTrigger
          render={(props, state) => (
            <ToolbarButton {...props} color="default">
              <Badge badgeContent={state.filterCount} color="primary" variant="dot">
                <FilterListIcon fontSize="small" />
              </Badge>
            </ToolbarButton>
          )}
        />
      </Tooltip>
    </Toolbar>
  );
};

export default DataTableToolbar;