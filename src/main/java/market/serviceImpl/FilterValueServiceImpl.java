package market.serviceImpl;

import lombok.RequiredArgsConstructor;
import market.dto.filterValue.FilterValueDto;
import market.entity.Filter;
import market.entity.FilterValue;
import market.repository.FilterRepository;
import market.repository.FilterValueRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilterValueServiceImpl implements market.service.FilterValueService {

    private final FilterValueRepository filterValueRepository;
    private final FilterRepository filterRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<FilterValue> getAll() {
        return this.filterValueRepository.findAllByOrderBySortOrderAsc();
    }

    @Override
    @Transactional
    public FilterValue findOneById(Long id) {
        return this.filterValueRepository.findOneById(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        this.filterValueRepository.deleteById(id);
    }

    @Override
    @Transactional
    public FilterValue create(FilterValueDto filterValueDto) {
        FilterValue newFilterValue = this.modelMapper.map(filterValueDto, FilterValue.class);
        if (filterValueDto.getFilterId() != null) {
            Filter filter = this.filterRepository.getById(filterValueDto.getFilterId());
            newFilterValue.setFilter(filter);
        }
        if (newFilterValue.getSortOrder() == null) {
            newFilterValue.setSortOrder(0L);
        }
        return this.filterValueRepository.save(newFilterValue);
    }

    @Override
    @Transactional
    public FilterValue update(FilterValueDto filterValueDto) {
        FilterValue filterValue = this.modelMapper.map(filterValueDto, FilterValue.class);
        if (filterValue.getSortOrder() == null) {
            filterValue.setSortOrder(0L);
        }
        return this.filterValueRepository.save(filterValue);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FilterValue> getAllByFilterId(Long filterId) {
        return filterValueRepository.findAllByFilterIdOrderBySortOrderAsc(filterId);
    }

    @Override
    @Transactional
    public void bulkDelete(List<Long> ids) {
        this.filterValueRepository.deleteAllByIdIn(ids);
    }
}
