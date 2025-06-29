package ee.askend.filters.domain;

import ee.askend.filters.domain.criteria.FilterCriteria;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Filter {
  @Id
  @GeneratedValue
  private Long id;

  private String name;

  @OneToMany(
    mappedBy = "filter",
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  private List<FilterCriteria> criteria = new ArrayList<>();
}
