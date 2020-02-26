package market.repository;

import market.entity.Filter;
import market.projection.filter.FilterView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilterRepository extends JpaRepository<Filter, Long> {

    Filter getById(Long id);

    List<FilterView> findAllBy();
}