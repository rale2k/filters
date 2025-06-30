import {
  BASE_URL,
  CLASSIFIERS_URI,
  POST_FILTER_URI,
} from '../../common/api/api'
import type { Filter } from '../../common/types/Filter'

export const fetchClassifiers = async (): Promise<any[] | any | undefined> => {
  try {
    const response = await fetch(`${BASE_URL}${CLASSIFIERS_URI}`)
    const result = await response.json()
    const fieldsWithOps = result.reduce((map: any, field: any) => {
      map[field.name] = field.supportedOperators
      return map
    }, {})

    const fieldsWithTypes = result.reduce((map: any, field: any) => {
      map[field.name] = field.dataType
      return map
    }, {})

    return [fieldsWithTypes, fieldsWithOps]
  } catch (error) {
    throw new Error('error fetching classifiers')
  }
}

export const postFilter = async (filter: Filter): Promise<Response> => {
  const response = await fetch(`${BASE_URL}${POST_FILTER_URI}`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(filter),
  })

  return response
}
