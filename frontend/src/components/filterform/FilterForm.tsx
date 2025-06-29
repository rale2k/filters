import { Add, Remove } from '@mui/icons-material'
import {
  Typography,
  Box,
  TextField,
  Button,
  FormControl,
  IconButton,
  MenuItem,
  Select,
  Alert,
} from '@mui/material'
import { useEffect, useState } from 'react'
import type { Filter } from '../../common/types/Filter'
import { fetchClassifiers, postFilter } from './actions'
import type { DataType } from '../../common/types/FilterField'
import CriteriaValueInput from '../criteriavalueinput'
import './style.css'

interface FilterProps {
  closeCallback: () => void
  createCallback: () => void
}

const FilterForm = (props: FilterProps) => {
  const { closeCallback, createCallback } = { ...props }
  const [filter, setFilterState] = useState<Filter>({
    name: 'Filter',
    criteria: [],
  })
  const [errors, setErrors] = useState<string[]>([])
  const [fieldsWithOps, setFieldsWithOps] = useState<Record<string, string[]>>(
    {}
  )
  const [fieldsWithTypes, setFieldsWithTypes] = useState<
    Record<string, DataType>
  >({})

  useEffect(() => {
    fetchClassifiers()
      .then((val) => {
        const [fieldsWithTypes, fieldsWithOps] = [...val!]
        setFieldsWithOps(fieldsWithOps!)
        setFieldsWithTypes(fieldsWithTypes!)
      })
      .catch((err) => {
        setErrors([`${err}`])
      })
  }, [])

  const handleAddCriteria = () => {
    setFilterState({
      ...filter,
      criteria: [
        ...filter.criteria,
        {
          field: Object.keys(fieldsWithOps)[0],
          operator: fieldsWithOps[Object.keys(fieldsWithOps)[0]][0],
        },
      ],
    })
  }

  const handleRemoveCriteria = (index: number): void => {
    const criteria = [...filter.criteria].filter((_, i) => i !== index)
    setFilterState({ ...filter, criteria: criteria })
  }

  const handleCriteriaFieldChange = (index: number, newValue: string): void => {
    const criteria = [...filter.criteria]
    criteria[index] = { ...criteria[index], field: newValue }
    setFilterState({ ...filter, criteria: criteria })
  }

  const handleCriteriaOperatorChange = (
    index: number,
    newValue: string
  ): void => {
    const criteria = [...filter.criteria]
    criteria[index] = { ...criteria[index], operator: newValue }
    setFilterState({ ...filter, criteria: criteria })
  }

  const handleCriteriaValueChange = (index: number, newValue: string): void => {
    const criteria = [...filter.criteria]
    criteria[index] = { ...criteria[index], value: newValue }
    setFilterState({ ...filter, criteria: criteria })
  }

  return (
    <>
      <Box className="filter-form-section">
        {errors.length > 0 && (
          <Alert severity="error" className="filter-form-alert">
            {errors.map((error, index) => (
              <div key={index}>{error}</div>
            ))}
          </Alert>
        )}
        <Typography variant="subtitle1" className="filter-form-label">
          Filter name
        </Typography>
        <TextField
          fullWidth
          value={filter.name}
          onChange={(e: React.ChangeEvent<HTMLInputElement>) =>
            setFilterState({ ...filter, name: e.target.value })
          }
          placeholder="Name"
          variant="outlined"
          size="small"
        />
      </Box>

      <Box className="filter-form-section">
        <Typography variant="subtitle1" className="filter-form-label">
          Criteria
        </Typography>

        {filter.criteria != null &&
          filter.criteria.map((criteria, index) => (
            <Box
              key={`${index}-${criteria.field}-${criteria.operator}`}
              className="filter-form-criteria-item"
            >
              <FormControl size="small" className="filter-form-field-select">
                <Select
                  value={criteria.field}
                  onChange={(e) =>
                    handleCriteriaFieldChange(index, e.target.value)
                  }
                >
                  {Object.keys(fieldsWithOps).map((option) => (
                    <MenuItem key={option} value={option}>
                      {option}
                    </MenuItem>
                  ))}
                </Select>
              </FormControl>
              <FormControl size="small" className="filter-form-operator-select">
                <Select
                  value={criteria.operator}
                  onChange={(e) =>
                    handleCriteriaOperatorChange(index, e.target.value)
                  }
                >
                  {fieldsWithOps[criteria.field!]?.map((option) => (
                    <MenuItem key={option} value={option}>
                      {option}
                    </MenuItem>
                  ))}
                </Select>
              </FormControl>
              <CriteriaValueInput
                criteria={criteria}
                fieldsWithTypes={fieldsWithTypes}
                handleCriteriaValueChange={handleCriteriaValueChange}
                index={index}
              />

              <IconButton
                onClick={() => handleRemoveCriteria(index)}
                disabled={filter.criteria.length < 2}
                className="filter-form-remove-button"
              >
                <Remove />
              </IconButton>
            </Box>
          ))}

        <Button
          startIcon={<Add />}
          onClick={handleAddCriteria}
          variant="outlined"
          size="small"
        >
          Add Criteria
        </Button>
      </Box>

      <Button
        onClick={async () => {
          const response = await postFilter(filter)
          console.log(response)
          if (response && !response.ok) {
            const json = await response.json()
            setErrors(json.errors)
          } else {
            closeCallback()
            createCallback()
          }
        }}
        variant="contained"
      >
        Save
      </Button>
      <Button
        onClick={() => {
          closeCallback()
        }}
        variant="outlined"
        color="inherit"
      >
        Close
      </Button>
    </>
  )
}

export default FilterForm
