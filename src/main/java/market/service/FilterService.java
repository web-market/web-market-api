package market.service;

import market.dto.filter.FilterDto;
import market.entity.Filter;
import market.projection.filter.FilterEditView;
import market.projection.filter.FilterView;

import java.util.List;

public interface FilterService {

    //Projections
    List<FilterView> getFilters();

    FilterEditView getFilter(Long id);

    //DTOs
    Filter getFullFilter(Long id);

    Filter create(FilterDto filterDto);

    Filter update(FilterDto filterDto);

    void deleteFilters(List<Long> ids);
}