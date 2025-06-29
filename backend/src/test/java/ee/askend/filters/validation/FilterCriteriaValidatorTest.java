package ee.askend.filters.validation;

import ee.askend.filters.domain.DataType;
import ee.askend.filters.domain.Field;
import ee.askend.filters.domain.Operator;
import ee.askend.filters.dto.FilterCriteriaDto;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static ee.askend.filters.domain.Operator.EQUAL;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FilterCriteriaValidatorTest {

  private FilterCriteriaValidator validator;

  @Mock
  private ConstraintValidatorContext constraintValidatorContext;
  @Mock
  private ConstraintValidatorContext.ConstraintViolationBuilder violationBuilder;
  @Mock
  private ConstraintValidatorContext.ConstraintViolationBuilder.NodeBuilderCustomizableContext nodeContext;

  @BeforeEach
  void setUp() {
    validator = new FilterCriteriaValidator();
  }

  @Test
  void shouldBeValid() {
    FilterCriteriaDto dto = new FilterCriteriaDto();
    assertTrue(validator.isValid(dto, constraintValidatorContext));

    dto.setField(null);
    dto.setOperator(EQUAL);
    assertTrue(validator.isValid(dto, constraintValidatorContext));

    dto.setField(mock(Field.class));
    dto.setOperator(null);
    assertTrue(validator.isValid(dto, constraintValidatorContext));

    Field field = mock(Field.class);
    DataType dataType = mock(DataType.class);
    when(field.getDataType()).thenReturn(dataType);
    when(dataType.getSupportedOperators()).thenReturn(List.of(EQUAL, Operator.GREATER_THAN));

    dto.setField(field);
    dto.setOperator(EQUAL);
    assertTrue(validator.isValid(dto, constraintValidatorContext));
  }

  @Test
  void shouldNotBeValid() {
    Field field = mock(Field.class);
    DataType dataType = mock(DataType.class);
    when(field.getDataType()).thenReturn(dataType);
    when(dataType.getSupportedOperators()).thenReturn(List.of(EQUAL));

    FilterCriteriaDto dto = new FilterCriteriaDto();
    dto.setField(field);
    dto.setOperator(Operator.GREATER_THAN);

    when(constraintValidatorContext.buildConstraintViolationWithTemplate(anyString()))
      .thenReturn(violationBuilder);
    when(violationBuilder.addPropertyNode("operator"))
      .thenReturn(nodeContext);
    when(nodeContext.addConstraintViolation())
      .thenReturn(constraintValidatorContext);

    assertFalse(validator.isValid(dto, constraintValidatorContext));
  }
}
