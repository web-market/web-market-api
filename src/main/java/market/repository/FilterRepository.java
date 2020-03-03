package market.repository;

import market.entity.Filter;
import market.projection.filter.FilterEditView;
import market.projection.filter.FilterView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilterRepository extends JpaRepository<Filter, Long> {

    Filter getById(Long id);

    FilterEditView findOneById(Long id);

    List<FilterView> findAllBy();

    void deleteAllByIdIn(List<Long> ids);
}