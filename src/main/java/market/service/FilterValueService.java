package market.service;

import market.dto.filterValue.FilterValueDto;
import market.entity.FilterValue;

import java.util.List;

public interface FilterValueService {

    List<FilterValue> getAll();

    List<FilterValue> getByIdIn(List<Long> ids);

    FilterValue findOneById(Long id);

    void delete(Long id);

    FilterValue create(FilterValueDto filterValueDto);

    FilterValue update(FilterValueDto filterValueDto);

    List<FilterValue> getAllByFilterId(Long filterId);

    void bulkDelete(List<Long> ids);
}
