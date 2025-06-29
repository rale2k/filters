package ee.askend.filters.domain.criteria;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class FilterCriteria {
  @Id
  @GeneratedValue
  private Long id;

  private String field;
  private String value;

  public abstract Enum<?> getOperator();

  public Long getId() {
    return id;
  }

  public String getField() {
    return field;
  }

  public String getValue() {
    return value;
  }
}
