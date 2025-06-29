package ee.askend.filters.service;

import ee.askend.filters.domain.Filter;
import ee.askend.filters.dto.FilterDto;
import ee.askend.filters.repository.FilterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class FilterServiceTest {

  @Mock
  private FilterRepository filterRepository;

  @InjectMocks
  private FilterService filterService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldReturnListOfFilters() {
    List<Filter> filters = Collections.singletonList(new Filter());
    when(filterRepository.findAll()).thenReturn(filters);

    List<FilterDto> result = filterService.getFilters();

    assertEquals(filters.size(), result.size());
    verify(filterRepository, times(1)).findAll();
  }

  @Test
  void shouldSaveFilter() {
    FilterDto filterDto = new FilterDto();
    filterDto.setCriteria(Collections.emptyList());
    filterService.createFilter(filterDto);
    verify(filterRepository, times(1)).save(any(Filter.class));
  }

  @Test
  void shouldDeleteFilterById() {
    Long filterId = 1L;
    filterService.deleteFilterById(filterId);
    verify(filterRepository, times(1)).deleteById(filterId);
  }
}
