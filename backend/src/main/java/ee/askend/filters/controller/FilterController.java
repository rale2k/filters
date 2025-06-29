package ee.askend.filters.controller;

import ee.askend.filters.service.FilterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/filter")
public class FilterController {
  private FilterService filterService;

  public FilterController(FilterService filterService) {
    this.filterService = filterService;
  }

  public
}
