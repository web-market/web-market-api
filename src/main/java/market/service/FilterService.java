package market.service;

import market.dto.filter.FilterDto;
import market.entity.Filter;

import java.util.List;

public interface FilterService {

    List<Filter> getAll();

    void delete(Long id);

    Filter create(FilterDto filterDto);

    Filter update(FilterDto filterDto);
}
