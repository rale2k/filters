package ee.askend.filters.repository;

import ee.askend.filters.domain.Filter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilterRepository extends JpaRepository<Filter, Long> {
}
