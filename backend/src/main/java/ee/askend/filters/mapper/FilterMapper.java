package ee.askend.filters.mapper;

import ee.askend.filters.domain.Filter;
import ee.askend.filters.domain.FilterCriteria;
import ee.askend.filters.dto.FilterCriteriaDto;
import ee.askend.filters.dto.FilterDto;

import java.util.Collections;
import java.util.List;

public class FilterMapper {

  public static FilterDto filterDtoToFilterEntity(Filter filter) {
    if (filter == null) {
      return null;
    }
    List<FilterCriteriaDto> criteriaDtos = Collections.emptyList();
    if (filter.getCriteria() != null) {
      criteriaDtos = filter.getCriteria().stream()
        .map(FilterMapper::criteriaToCriteriaDto)
        .toList();
    }
    FilterDto filterDto = new FilterDto();
    filterDto.setId(filter.getId());
    filterDto.setName(filter.getName());
    filterDto.setCriteria(criteriaDtos);
    return filterDto;
  }

  public static Filter filterEntityToFilterDto(FilterDto dto) {
    if (dto == null) {
      return null;
    }
    Filter filter = new Filter();
    List<FilterCriteria> criteria = Collections.emptyList();
    if (dto.getCriteria() != null) {
      criteria = dto.getCriteria().stream()
        .map(FilterMapper::criteriaDtoToCriteria)
        .toList();
    }

    filter.setId(dto.getId());
    filter.setName(dto.getName());
    filter.setCriteria(criteria);
    return filter;
  }

  public static FilterCriteriaDto criteriaToCriteriaDto(FilterCriteria criteria) {
    if (criteria == null) {
      return null;
    }
    FilterCriteriaDto dto = new FilterCriteriaDto();
    dto.setId(criteria.getId());
    dto.setField(criteria.getField());
    dto.setOperator(criteria.getOperator());
    dto.setValue(criteria.getValue());
    return dto;
  }

  public static FilterCriteria criteriaDtoToCriteria(FilterCriteriaDto dto) {
    if (dto == null) {
      return null;
    }
    FilterCriteria entity = new FilterCriteria();
    entity.setId(dto.getId());
    entity.setField(dto.getField());
    entity.setOperator(dto.getOperator());
    entity.setValue(dto.getValue());
    return entity;
  }
}

