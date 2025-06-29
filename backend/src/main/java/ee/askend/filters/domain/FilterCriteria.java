package ee.askend.filters.domain;

import jakarta.persistence.*;

@Entity
public class FilterCriteria {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name="filter_id", nullable=false)
  private Filter filter;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Field field;

  @Column(nullable = false, name = "`value`")
  private String value;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Operator operator;

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

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public Operator getOperator() {
    return operator;
  }

  public void setOperator(Operator operator) {
    this.operator = operator;
  }

  @PrePersist @PreUpdate
  private void validateOperator() {
    DataType dataType = field.getDataType();
    if (!dataType.getSupportedOperators().contains(operator)) {
      throw new IllegalArgumentException(
        String.format("Operator %s is not allowed for data type %s", operator, dataType)
      );
    }
  }

  public void setFilter(Filter filter) {
    this.filter = filter;
  }
}
