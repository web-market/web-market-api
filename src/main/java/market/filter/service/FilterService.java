package market.filter.service;

import market.filter.dto.FilterDto;
import market.entity.Filter;
import market.filter.dto.FilterEditView;
import market.filter.dto.FilterView;

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