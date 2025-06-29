export type DataType = 'NUMERIC' | 'TEXT' | 'DATE'

export interface FilterField {
  name: string
  dataType: DataType
  supportedOperators: string[]
}
