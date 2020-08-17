package market.repository;

import market.entity.FilterValue;
import market.projection.filterValue.FilterValueView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilterValueRepository extends JpaRepository<FilterValue, Long> {

    //Projections
    List<FilterValueView> getAllByOrderBySortOrderDesc();

    List<FilterValueView> getAllByIdIn(List<Long> id);

    List<FilterValueView> getAllByFilterIdOrderBySortOrderDesc(Long filterId);

    //DTOs
    List<FilterValue> findAllByIdIn(List<Long> id);

    FilterValueView getById(Long id);

    void deleteAllByIdIn(List<Long> ids);
}