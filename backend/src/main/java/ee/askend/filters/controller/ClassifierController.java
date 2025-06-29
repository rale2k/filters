package ee.askend.filters.controller;

import ee.askend.filters.dto.FilterDto;
import ee.askend.filters.service.FilterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
  public void createFilter(@RequestBody FilterDto dto) {
    filterService.createFilter(dto);
  }
//
//  @PutMapping("/{id}")
//  public void updateFilter(@PathVariable Long id) {
//    return;
//  }

  @PostMapping("/{id}")
  public void deleteFilter(@PathVariable Long id) {
    filterService.deleteFilterById(id);
  }
}
