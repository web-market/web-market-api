package market.serviceImpl;

import lombok.RequiredArgsConstructor;
import market.dto.filter.FilterDto;
import market.dto.filterValue.FilterValueDto;
import market.entity.Category;
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
    public List<FilterValue> getAll() {
        return this.filterValueRepository.findAll();
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
        return this.filterValueRepository.save(newFilterValue);
    }

    @Override
    @Transactional
    public FilterValue update(FilterValueDto filterValueDto) {
        FilterValue newFilterValue = this.modelMapper.map(filterValueDto, FilterValue.class);
        if (filterValueDto.getFilterId() != null) {
            Filter filter = this.filterRepository.getById(filterValueDto.getFilterId());
            newFilterValue.setFilter(filter);
        }
        return this.filterValueRepository.save(newFilterValue);
    }

    @Override
    public List<FilterValue> getAllByFilterId(Long filterId) {
        return filterValueRepository.findAllByFilterId(filterId);
    }
}
