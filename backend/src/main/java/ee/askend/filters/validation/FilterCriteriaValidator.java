package ee.askend.filters.validation;

import ee.askend.filters.domain.Operator;
import ee.askend.filters.dto.FilterCriteriaDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class FilterCriteriaValidator implements ConstraintValidator<FilterValidityCriteria, FilterCriteriaDto> {

  @Override
  public boolean isValid(FilterCriteriaDto dto, ConstraintValidatorContext ctx) {
    if (dto.getField() == null || dto.getOperator() == null) {
      return true;
    }

    List<Operator> supported = dto.getField()
      .getDataType()
      .getSupportedOperators();

    if (!supported.contains(dto.getOperator())) {
      ctx.disableDefaultConstraintViolation();
      ctx.buildConstraintViolationWithTemplate(
          String.format("Operator '%s' is not valid for field '%s'",
            dto.getOperator(), dto.getField()))
        .addPropertyNode("operator")
        .addConstraintViolation();
      return false;
    }
    return true;
  }
}
