package market.filterValue;

import market.entity.FilterValue;
import market.filterValue.dto.FilterValueView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilterValueRepository extends JpaRepository<FilterValue, Long> {

    //Projections
    List<FilterValueView> findAllByOrderBySortOrderDesc();

    List<FilterValueView> findAllByIdIn(List<Long> id);

    List<FilterValueView> findAllByFilterIdOrderBySortOrderDesc(Long filterId);

    //DTOs
    List<FilterValue> getAllByIdIn(List<Long> id);

    FilterValueView getById(Long id);

    void deleteAllByIdIn(List<Long> ids);
}