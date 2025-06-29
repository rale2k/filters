package ee.askend.filters.domain;

import jakarta.persistence.*;

import java.util.List;

import static java.util.Collections.emptyList;

@Entity
public class Filter {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  private String name;

  @OneToMany(
    mappedBy = "filter",
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  private List<FilterCriteria> criteria = emptyList();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<FilterCriteria> getCriteria() {
    return criteria;
  }

  public void setCriteria(List<FilterCriteria> criteria) {
    this.criteria = criteria;
  }
}
