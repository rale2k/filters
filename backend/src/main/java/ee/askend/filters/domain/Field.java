package ee.askend.filters.domain;


public enum Field {
  AMOUNT(DataType.NUMERIC),
  TITLE(DataType.TEXT),
  DATE(DataType.DATE),;

  private final DataType dataType;

  Field(DataType dataType) {
    this.dataType = dataType;
  }

  public DataType getDataType() {
    return dataType;
  }
}
