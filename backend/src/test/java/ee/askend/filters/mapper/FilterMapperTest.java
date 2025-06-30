package ee.askend.filters.mapper;

import ee.askend.filters.domain.Field;
import ee.askend.filters.domain.Filter;
import ee.askend.filters.domain.FilterCriteria;
import ee.askend.filters.domain.Operator;
import ee.askend.filters.dto.FilterCriteriaDto;
import ee.askend.filters.dto.FilterDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class FilterMapperTest {

  @Test
  void shouldMapFilterDtoToFilterEntity() {
    FilterCriteria criteria = new FilterCriteria();
    criteria.setId(1L);
    criteria.setField(Field.AMOUNT);
    criteria.setOperator(Operator.GREATER_THAN);
    criteria.setValue("112312300");

    Filter filter = new Filter();
    filter.setId(1L);
    filter.setName("wada");
    filter.setCriteria(List.of(criteria));

    FilterDto dto = FilterMapper.filterDtoToFilterEntity(filter);

    assertNotNull(dto);
    assertEquals(filter.getId(), dto.getId());
    assertEquals(filter.getName(), dto.getName());
    assertEquals(filter.getCriteria().size(), dto.getCriteria().size());
    assertEquals(filter.getCriteria().getFirst().getField(), dto.getCriteria().getFirst().getField());
    assertEquals(filter.getCriteria().getFirst().getOperator(), dto.getCriteria().getFirst().getOperator());
    assertEquals(filter.getCriteria().getFirst().getValue(), dto.getCriteria().getFirst().getValue());

    assertNull(FilterMapper.filterDtoToFilterEntity(null));
  }

  @Test
  void shouldMapFilterEntityToFilterDto() {
    FilterCriteriaDto criteriaDto = new FilterCriteriaDto();
    criteriaDto.setId(1L);
    criteriaDto.setField(Field.TITLE);
    criteriaDto.setOperator(Operator.STARTS_WITH);
    criteriaDto.setValue("shi");

    FilterDto dto = new FilterDto();
    dto.setId(1L);
    dto.setName("qwery");
    dto.setCriteria(List.of(criteriaDto));

    Filter filter = FilterMapper.filterEntityToFilterDto(dto);

    assertNotNull(filter);
    assertEquals(dto.getId(), filter.getId());
    assertEquals(dto.getName(), filter.getName());
    assertEquals(dto.getCriteria().size(), filter.getCriteria().size());
    assertEquals(dto.getCriteria().getFirst().getField(), filter.getCriteria().getFirst().getField());
    assertEquals(dto.getCriteria().getFirst().getOperator(), filter.getCriteria().getFirst().getOperator());
    assertEquals(dto.getCriteria().getFirst().getValue(), filter.getCriteria().getFirst().getValue());

    assertNull(FilterMapper.filterEntityToFilterDto(null));
  }

  @Test
  void shouldMapCriteriaToCriteriaDto() {
    FilterCriteria criteria = new FilterCriteria();
    criteria.setId(1L);
    criteria.setField(Field.AMOUNT);
    criteria.setOperator(Operator.GREATER_THAN);
    criteria.setValue("100");

    FilterCriteriaDto dto = FilterMapper.criteriaToCriteriaDto(criteria);

    assertNotNull(dto);
    assertEquals(criteria.getId(), dto.getId());
    assertEquals(criteria.getField(), dto.getField());
    assertEquals(criteria.getOperator(), dto.getOperator());
    assertEquals(criteria.getValue(), dto.getValue());

    assertNull(FilterMapper.criteriaToCriteriaDto(null));
  }

  @Test
  void shouldMapCriteriaDtoToCriteria() {
    FilterCriteriaDto dto = new FilterCriteriaDto();
    dto.setId(1L);
    dto.setField(Field.TITLE);
    dto.setOperator(Operator.STARTS_WITH);
    dto.setValue("Test");

    FilterCriteria criteria = FilterMapper.criteriaDtoToCriteria(dto);

    assertNotNull(criteria);
    assertEquals(dto.getId(), criteria.getId());
    assertEquals(dto.getField(), criteria.getField());
    assertEquals(dto.getOperator(), criteria.getOperator());
    assertEquals(dto.getValue(), criteria.getValue());

    assertNull(FilterMapper.criteriaDtoToCriteria(null));
  }
}
