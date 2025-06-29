package ee.askend.filters.controller;

import ee.askend.filters.dto.FilterDto;
import ee.askend.filters.service.FilterService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/filter")
public class FilterController {
  private final FilterService filterService;

  public FilterController(FilterService filterService) {
    this.filterService = filterService;
  }

  @GetMapping
  public List<FilterDto> getFilters() {
    return filterService.getFilters();
  }

  @PostMapping
  public ResponseEntity<?> createFilter(@Valid @RequestBody FilterDto dto,
                                        BindingResult br) {
    if (br.hasErrors()) {
      var errors = br.getFieldErrors().stream()
        .map(e -> e.getField() + ": " + e.getDefaultMessage())
        .toList();
      return ResponseEntity.badRequest()
        .body(Map.of("errors", errors));
    }

    filterService.createFilter(dto);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteFilter(@PathVariable Long id) {
    filterService.deleteFilterById(id);

    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
