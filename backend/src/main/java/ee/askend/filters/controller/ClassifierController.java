package ee.askend.filters.controller;

import ee.askend.filters.domain.Field;
import ee.askend.filters.domain.Operator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/classifiers")
public class ClassifierController {
  @GetMapping
  public List<Map<String, Object>> getClassifiers() {
    return Arrays.stream(Field.values())
      .map(field -> {
        Map<String, Object> m = new HashMap<>();
        m.put("name", field.name());
        m.put("dataType", field.getDataType().name());
        List<String> supportedOperations = field
          .getDataType()
          .getSupportedOperators()
          .stream()
          .map(Operator::name)
          .collect(Collectors.toList());
        m.put("supportedOperators", supportedOperations);
        return m;
      })
      .toList();
  }
}
