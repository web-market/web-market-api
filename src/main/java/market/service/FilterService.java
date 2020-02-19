package market.service;

import market.entity.Filter;

import java.util.List;

public interface FilterService {

    List<Filter> getAll();

    void delete(Long id);
}
