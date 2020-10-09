package market.filter.service.impl;

import lombok.RequiredArgsConstructor;
import market.filter.dto.FilterDto;
import market.entity.Filter;
import market.filter.dto.FilterEditView;
import market.filter.dto.FilterView;
import market.filter.FilterRepository;
import market.filter.service.FilterService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilterServiceImpl implements FilterService {

    private final FilterRepository filterRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<FilterView> getFilters() {
        return this.filterRepository.findAllByOrderBySortOrderAsc();
    }

    @Override
    @Transactional(readOnly = true)
    public FilterEditView getFilter(Long id) {
        return this.filterRepository.findOneById(id);
    }

    @Override
    public Filter getFullFilter(Long id) {
        return this.filterRepository.getById(id);
    }

    @Override
    @Transactional
    public Filter create(FilterDto filterDto) {
        Filter newFilter = this.modelMapper.map(filterDto, Filter.class);
        return this.filterRepository.save(newFilter);
    }

    @Override
    @Transactional
    public Filter update(FilterDto filterDto) {
        Filter filter = this.modelMapper.map(filterDto, Filter.class);
        return this.filterRepository.save(filter);
    }

    @Override
    @Transactional
    public void deleteFilters(List<Long> ids) {
        this.filterRepository.deleteAllByIdIn(ids);
    }
}
