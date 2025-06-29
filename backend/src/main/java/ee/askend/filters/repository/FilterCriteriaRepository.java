package ee.askend.filters.repository;

import ee.askend.filters.domain.FilterCriteria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilterCriteriaRepository extends JpaRepository<FilterCriteria, Long> {
}
