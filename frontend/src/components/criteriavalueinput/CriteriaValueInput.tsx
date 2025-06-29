import { DatePicker } from '@mui/x-date-pickers/DatePicker'
import { TextField } from '@mui/material'
import type { Criteria } from '../../common/types/Criteria'
import dayjs from 'dayjs'
import './style.css'

interface CriteriaValueInputProps {
  criteria: Criteria
  fieldsWithTypes: Record<string, string>
  handleCriteriaValueChange: (index: number, value: string) => void
  index: number
}

const CriteriaValueInput = (props: CriteriaValueInputProps) => {
  const { criteria, fieldsWithTypes, handleCriteriaValueChange, index } = {
    ...props,
  }

  return (
    <>
      {fieldsWithTypes[criteria.field!] === 'DATE' && (
        <DatePicker
          className="date-picker-input"
          value={criteria.value ? dayjs(criteria.value) : undefined}
          onChange={(e) =>
            handleCriteriaValueChange(index, e?.format('DD-MM-YYYY')!)
          }
        />
      )}
      {fieldsWithTypes[criteria.field!] === 'NUMERIC' && (
        <TextField
          size="small"
          value={criteria.value}
          type="number"
          onChange={(e) => handleCriteriaValueChange(index, e.target.value)}
          className="text-field-flex"
          placeholder="Enter value"
        />
      )}
      {fieldsWithTypes[criteria.field!] === 'TEXT' && (
        <TextField
          size="small"
          value={criteria.value}
          onChange={(e) => handleCriteriaValueChange(index, e.target.value)}
          className="text-field-flex"
          placeholder="Enter value"
        />
      )}
    </>
  )
}

export default CriteriaValueInput
