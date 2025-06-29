package ee.askend.filters.service;

import ee.askend.filters.domain.Filter;
import ee.askend.filters.dto.FilterDto;
import ee.askend.filters.mapper.FilterMapper;
import ee.askend.filters.repository.FilterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FilterService {
  private final FilterRepository filterRepository;

  public FilterService(FilterRepository filterRepository) {
    this.filterRepository = filterRepository;
  }

  @Transactional
  public List<FilterDto> getFilters() {
    List<Filter> filters = filterRepository.findAll();
    return filters.stream().map(FilterMapper::filterDtoToFilterEntity).toList();
  }

  @Transactional
  public void createFilter(FilterDto filterDto) {
    Filter filter = FilterMapper.filterEntityToFilterDto(filterDto);
    filter.getCriteria().forEach(filterCriteria -> filterCriteria.setFilter(filter));

    filterRepository.save(filter);
  }

  @Transactional
  public void deleteFilterById(Long id) {
    filterRepository.deleteById(id);
  }

}
