package ee.askend.filters.dto;

import ee.askend.filters.domain.Field;
import ee.askend.filters.domain.Operator;
import ee.askend.filters.validation.FilterValidityCriteria;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@FilterValidityCriteria
public class FilterCriteriaDto {
  private Long id;
  @NotNull(message = "Field is required")
  private Field field;
  @NotNull(message = "Operator is required")
  private Operator operator;
  @NotNull(message = "Value is required")
  @NotEmpty(message = "Value must not be empty")
  private String value;

  public FilterCriteriaDto() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Field getField() {
    return field;
  }

  public void setField(Field field) {
    this.field = field;
  }

  public Operator getOperator() {
    return operator;
  }

  public void setOperator(Operator operator) {
    this.operator = operator;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
