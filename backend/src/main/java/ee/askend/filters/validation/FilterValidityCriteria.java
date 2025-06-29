package ee.askend.filters.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FilterCriteriaValidator.class)
@Documented
public @interface FilterValidityCriteria {
  String message() default "Operator is not supported by the field";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
