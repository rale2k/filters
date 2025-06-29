import { URI } from 'uri-template-lite'
import { BASE_URL, DELETE_FILTER_URI, GET_FILTER_URI } from '../common/api/api'

export const fetchFilters = async (): Promise<Response> => {
  const response = await fetch(`${BASE_URL}${GET_FILTER_URI}`)
  return response
}

export const deleteFilter = async (id: number): Promise<any> => {
  const template = new URI.Template(`${BASE_URL}${DELETE_FILTER_URI}`)
  await fetch(template.expand({ id }), {
    method: 'DELETE',
  })
}
