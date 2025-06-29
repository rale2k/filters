package ee.askend.filters.controller;

import ee.askend.filters.domain.Field;
import ee.askend.filters.domain.Operator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClassifierControllerTest {

  private ClassifierController classifierController;

  @BeforeEach
  void setUp() {
    classifierController = new ClassifierController();
  }

  @Test
  void shouldGetClassifiers() {
    List<Map<String, Object>> result = classifierController.getClassifiers();

    assertNotNull(result);
    assertEquals(Field.values().length, result.size());

    for (Field fieldEnum : Field.values()) {
      Map<String, Object> mappedField = result.stream()
        .filter(f -> fieldEnum.name().equals(f.get("name")))
        .findFirst()
        .orElse(null);

      assertNotNull(mappedField);
      assertEquals(fieldEnum.name(), mappedField.get("name"));
      assertEquals(fieldEnum.getDataType().name(), mappedField.get("dataType"));

      @SuppressWarnings("unchecked")
      List<String> supportedOperators = (List<String>) mappedField.get("supportedOperators");
      List<String> expectedOperators = fieldEnum.getDataType().getSupportedOperators().stream()
        .map(Operator::name)
        .toList();

      assertEquals(expectedOperators.size(), supportedOperators.size());
      assertTrue(supportedOperators.containsAll(expectedOperators));
      assertTrue(expectedOperators.containsAll(supportedOperators));
    }
  }
}
