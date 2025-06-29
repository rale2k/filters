import {
  Box,
  Typography,
  Paper,
  TableContainer,
  Table,
  TableHead,
  TableRow,
  TableCell,
  TableBody,
  IconButton,
  Chip,
} from '@mui/material'
import { Delete } from '@mui/icons-material'
import type { Filter } from '../../common/types/Filter'
import './style.css'

interface FilterTableProps {
  filters: Filter[]
  deleteFilter: (id: number) => void
}

const FilterTable = ({ filters, deleteFilter }: FilterTableProps) => {
  return (
    <Paper>
      <TableContainer>
        <Table stickyHeader>
          <TableHead>
            <TableRow>
              <TableCell className="filter-table-header-cell">Name</TableCell>
              <TableCell className="filter-table-header-cell">Criteria</TableCell>
              <TableCell className="filter-table-header-cell">Actions</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {filters.length === 0 ? (
              <TableRow>
                <TableCell colSpan={5} align="center" className="filter-table-empty-cell">
                  <Typography variant="body1" color="textSecondary">
                    No filters
                  </Typography>
                </TableCell>
              </TableRow>
            ) : (
              filters.map((filter) => (
                <TableRow key={filter.id} hover>
                  <TableCell className="filter-name-cell">
                    <Typography variant="subtitle1" fontWeight="medium">
                      {filter.name}
                    </Typography>
                  </TableCell>
                  <TableCell>
                    <Box
                      className="filter-criteria-box"
                    >
                      {filter.criteria.map((criteria) => (
                        <Chip
                          key={criteria.id}
                          label={`${criteria.field} ${criteria.operator} ${criteria.value}`}
                          size="small"
                          variant="outlined"
                          className="filter-chip"
                        />
                      ))}
                    </Box>
                  </TableCell>
                  <TableCell>
                    <Box className="filter-actions-box">
                      <IconButton
                        size="small"
                        onClick={() => {
                          deleteFilter(filter.id as number)
                        }}
                        color="error"
                      >
                        <Delete />
                      </IconButton>
                    </Box>
                  </TableCell>
                </TableRow>
              ))
            )}
          </TableBody>
        </Table>
      </TableContainer>
    </Paper>
  )
}

export default FilterTable
