package market.repository;

import market.entity.Filter;
import market.projection.filter.FilterEditView;
import market.projection.filter.FilterView;
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