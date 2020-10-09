package market.filter;

import market.entity.Filter;
import market.filter.dto.FilterEditView;
import market.filter.dto.FilterView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilterRepository extends JpaRepository<Filter, Long> {

    //Projections
    List<FilterView> findAllByOrderBySortOrderAsc();

    FilterEditView findOneById(Long id);

    //DTOs
    Filter getById(Long id);

    void deleteAllByIdIn(List<Long> ids);
}