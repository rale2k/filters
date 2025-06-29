package ee.askend.filters.domain;

import java.util.List;

import static ee.askend.filters.domain.Operator.*;

public enum DataType {
  NUMERIC(
    List.of(
      EQUAL,
      NOT_EQUAL,
      GREATER_THAN,
      LESS_THAN,
      GREATER_THAN_OR_EQUAL,
      LESS_THAN_OR_EQUAL)
  ),

  TEXT(
    List.of(
      EQUAL,
      NOT_EQUAL,
      CONTAINS,
      STARTS_WITH,
      ENDS_WITH)
  ),

  DATE(
    List.of(
      EQUAL,
      NOT_EQUAL,
      GREATER_THAN,
      LESS_THAN,
      GREATER_THAN_OR_EQUAL,
      LESS_THAN_OR_EQUAL)
  );

  private final List<Operator> supportedOperators;

  DataType(List<Operator> supportedOperators) {
    this.supportedOperators = supportedOperators;
  }

  public List<Operator> getSupportedOperators() {
    return supportedOperators;
  }
}
