package market.service;

import market.dto.filter.FilterDto;
import market.entity.Filter;
import market.projection.filter.FilterEditView;
import market.projection.filter.FilterView;

import java.util.List;

public interface FilterService {

    List<FilterView> getAll();

    FilterEditView findOneById(Long id);

    void delete(Long id);

    Filter create(FilterDto filterDto);

    Filter update(FilterDto filterDto);

    void bulkDelete(List<Long> ids);
}