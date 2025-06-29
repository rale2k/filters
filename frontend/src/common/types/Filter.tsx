import type { Criteria } from './Criteria'

export interface Filter {
  id?: number
  name?: string
  criteria: Criteria[]
}
