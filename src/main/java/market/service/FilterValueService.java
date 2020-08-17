package market.service;

import market.dto.filterValue.FilterValueDto;
import market.entity.FilterValue;
import market.projection.filterValue.FilterValueView;

import java.util.List;

public interface FilterValueService {

    //Projections
    List<FilterValueView> getFilterValues();

    List<FilterValueView> getAllByFilter(Long filterId);

    List<FilterValueView> getMentionedFilterValues(List<Long> ids);

    FilterValueView getFilterValue(Long id);

    //DTOs
    List<FilterValue> getFullMentionedFilterValues(List<Long> ids);

    FilterValue create(FilterValueDto filterValueDto);

    FilterValue update(FilterValueDto filterValueDto);

    void delete(Long id);

    void bulkDelete(List<Long> ids);
}
