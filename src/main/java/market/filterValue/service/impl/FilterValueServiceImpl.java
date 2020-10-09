package market.filterValue.service.impl;

import lombok.RequiredArgsConstructor;
import market.filterValue.dto.FilterValueDto;
import market.entity.FilterValue;
import market.filterValue.dto.FilterValueView;
import market.filterValue.FilterValueRepository;
import market.filter.service.FilterService;
import market.filterValue.service.FilterValueService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilterValueServiceImpl implements FilterValueService {

    private final FilterService filterService;
    private final FilterValueRepository filterValueRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<FilterValueView> getFilterValues() {
        return this.filterValueRepository.findAllByOrderBySortOrderDesc();
    }

    @Override
    @Transactional(readOnly = true)
    public List<FilterValueView> getAllByFilter(Long filterId) {
        return filterValueRepository.findAllByFilterIdOrderBySortOrderDesc(filterId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FilterValueView> getMentionedFilterValues(List<Long> ids) {
        return this.filterValueRepository.findAllByIdIn(ids);
    }

    @Override
    @Transactional(readOnly = true)
    public FilterValueView getFilterValue(Long id) {
        return this.filterValueRepository.getById(id);
    }

    @Override
    public List<FilterValue> getFullMentionedFilterValues(List<Long> ids) {
        return this.filterValueRepository.getAllByIdIn(ids);
    }

    @Override
    @Transactional
    public FilterValue create(FilterValueDto filterValueDto) {
        FilterValue newFilterValue = this.modelMapper.map(filterValueDto, FilterValue.class);
        newFilterValue.setFilter(this.filterService.getFullFilter(filterValueDto.getFilterId()));
        return this.filterValueRepository.save(newFilterValue);
    }

    @Override
    @Transactional
    public FilterValue update(FilterValueDto filterValueDto) {
        FilterValue filterValue = this.modelMapper.map(filterValueDto, FilterValue.class);
        return this.filterValueRepository.save(filterValue);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        this.filterValueRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void bulkDelete(List<Long> ids) {
        this.filterValueRepository.deleteAllByIdIn(ids);
    }
}
