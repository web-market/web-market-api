package market.repository;

import market.entity.FilterValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilterValueRepository extends JpaRepository<FilterValue, Long> {

    List<FilterValue> findAllByFilterId(Long filterId);
}