package market.repository;

import market.entity.Category;
import market.entity.FilterValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilterValueRepository extends JpaRepository<FilterValue, Long> {

    List<FilterValue> findAllByOrderBySortOrderAsc();

    List<FilterValue> getAllByIdIn(List<Long> id);

    FilterValue findOneById(Long id);

    List<FilterValue> findAllByFilterIdOrderBySortOrderAsc(Long filterId);

    void deleteAllByIdIn(List<Long> ids);
}