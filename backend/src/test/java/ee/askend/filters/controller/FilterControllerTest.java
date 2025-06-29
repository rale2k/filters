package ee.askend.filters.controller;

import ee.askend.filters.dto.FilterDto;
import ee.askend.filters.service.FilterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FilterControllerTest {

  @Mock
  private FilterService filterService;

  @InjectMocks
  private FilterController filterController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldReturnListOfFilters() {
    List<FilterDto> expectedFilters = singletonList(new FilterDto());
    when(filterService.getFilters()).thenReturn(expectedFilters);

    List<FilterDto> actualFilters = filterController.getFilters();

    assertEquals(expectedFilters, actualFilters);
    verify(filterService, times(1)).getFilters();
  }

  @Test
  void shouldCreateFilterReturnsCreatedStatusWhenValid() {
    FilterDto filterDto = new FilterDto();
    BindingResult bindingResult = mock(BindingResult.class);
    when(bindingResult.hasErrors()).thenReturn(false);

    ResponseEntity<?> response = filterController.createFilter(filterDto, bindingResult);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    verify(filterService, times(1)).createFilter(filterDto);
  }

  @Test
  void createFilterReturnsBadRequestWhenInvalid() {
    FilterDto filterDto = new FilterDto();
    BindingResult bindingResult = mock(BindingResult.class);
    when(bindingResult.hasErrors()).thenReturn(true);
    when(bindingResult.getFieldErrors()).thenReturn(
      singletonList(new FieldError("filterDto", "name", "Name is required")));

    ResponseEntity<?> response = filterController.createFilter(filterDto, bindingResult);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertInstanceOf(Map.class, response.getBody());
    @SuppressWarnings("unchecked")
    Map<String, List<String>> errorMap = (Map<String, List<String>>) response.getBody();
    assertTrue(errorMap.containsKey("errors"));
    assertEquals(1, errorMap.get("errors").size());
    assertEquals("name: Name is required", errorMap.get("errors").getFirst());
    verify(filterService, never()).createFilter(filterDto);
  }

  @Test
  void shouldDeleteFilter() {
    Long filterId = 1L;

    ResponseEntity<?> response = filterController.deleteFilter(filterId);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    verify(filterService, times(1)).deleteFilterById(filterId);
  }
}
