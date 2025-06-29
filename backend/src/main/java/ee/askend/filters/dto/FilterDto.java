package ee.askend.filters.dto;

import java.util.List;

public class Filter {
  private Long id;
  private String name;
  private List<FilterCriteria> filterCriterias;

  public Filter(Long id, String name, List<FilterCriteria> filterCriterias) {
    this.id = id;
    this.name = name;
    this.filterCriterias = filterCriterias;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public List<FilterCriteria> getFilterCriterias() {
    return filterCriterias;
  }
}
