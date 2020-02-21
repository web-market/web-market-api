package market.repository;

import market.entity.Filter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilterRepository extends JpaRepository<Filter, Long> {

    Filter getById(Long id);
}