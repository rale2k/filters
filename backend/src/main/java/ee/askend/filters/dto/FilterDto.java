package ee.askend.filters.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;


public class FilterDto {
  private Long id;
  @NotNull(message = "Name is required")
  @NotEmpty(message = "Name must not be empty")
  private String name;
  @Valid
  @Size(min = 1, message = "Filter must have at least one criteria")
  private List<FilterCriteriaDto> criteria;

  public FilterDto() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<FilterCriteriaDto> getCriteria() {
    return criteria;
  }

  public void setCriteria(List<FilterCriteriaDto> criteria) {
    this.criteria = criteria;
  }
}
