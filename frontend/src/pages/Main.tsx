import {
  Container,
  Box,
  Typography,
  Button,
  Paper,
  ToggleButton,
  ToggleButtonGroup,
  Stack,
  Alert,
} from '@mui/material'
import './style.css'
import { FilterList, List, ListAlt } from '@mui/icons-material'
import { FilterProvider } from '../common/types/FilterProvider'
import { useEffect, useState, type SetStateAction } from 'react'
import Modal from '../components/modal'
import type { Filter } from '../common/types/Filter'
import FilterForm from '../components/filterform'
import { deleteFilter, fetchFilters } from './actions'
import FilterTable from '../components/filtertable'

const Main = () => {
  const [showFilter, setShowFilter] = useState(false)
  const [filterProvider, setFilterProvider] = useState(FilterProvider.MODAL)
  const [filters, setFilters] = useState<Filter[]>([])
  const [errors, setErrors] = useState<string[]>([])

  useEffect(() => {
    loadFilters();
  }, [])

  const handleSetFilterProvider = (
    _: any,
    newValue: SetStateAction<FilterProvider> | null
  ) => {
    if (newValue != null) {
      setFilterProvider(newValue)
    }
  }

  const handleDeleteFilter = (id: number) => {
    deleteFilter(id).then(() => {
      const newFilters = filters.filter((f) => f.id != id)
      setFilters(newFilters)
    })
  }

  const loadFilters = () => {
    fetchFilters()
      .then(async (response) => {
        const json = await response.json()
        if (response && !response.ok) {
          setErrors([json.error])
        } else {
          setFilters(json)
        }
      })
      .catch((err) => setErrors([err]))
  }

  if (errors && errors.length > 0) {
    return (
      <Alert severity="error" className="filter-form-alert">
        {errors.map((error, index) => (
          <div key={index}>{error}</div>
        ))}
      </Alert>
    )
  }

  return (
    <Container maxWidth="lg" className="main-container">
      <Box className="header-box">
        <Box className="header-left-box">
          <FilterList className="filter-icon" sx={{ color: 'primary.main' }} />
          <Typography variant="h4" component="h1" fontWeight="bold">
            Filter Management
          </Typography>
        </Box>
        <Box className="header-right-box">
          <Button
            variant="contained"
            size="large"
            onClick={() => setShowFilter(true)}
          >
            Add Filter
          </Button>
          <ToggleButtonGroup
            value={filterProvider}
            exclusive
            onChange={handleSetFilterProvider}
          >
            <ToggleButton value={FilterProvider.PAGE}>
              <List />
            </ToggleButton>
            <ToggleButton value={FilterProvider.MODAL}>
              <ListAlt />
            </ToggleButton>
          </ToggleButtonGroup>
        </Box>
      </Box>
      <Stack spacing={2}>
        {showFilter && filterProvider === FilterProvider.PAGE && (
          <Paper>
            <Box className="filter-paper">
              <Typography variant="h5" gutterBottom className="add-filter-title" sx={{ backgroundColor: 'primary.main' }}>
                Add Filter
              </Typography>
              <FilterForm closeCallback={() => setShowFilter(false)} createCallback={() => loadFilters()} />
            </Box>
          </Paper>
        )}
        <FilterTable filters={filters} deleteFilter={handleDeleteFilter} />
      </Stack>
      <Modal
        open={showFilter && filterProvider === FilterProvider.MODAL}
        handleClose={() => setShowFilter(!showFilter)}
        children={<FilterForm closeCallback={() => setShowFilter(false)} createCallback={() => loadFilters()} />} modalTitle={'Add filter'} />
    </Container>
  )
}

export default Main
