package market.serviceImpl;

import lombok.RequiredArgsConstructor;
import market.dto.filter.FilterDto;
import market.entity.Filter;
import market.projection.filter.FilterEditView;
import market.projection.filter.FilterView;
import market.repository.FilterRepository;
import market.service.FilterService;
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
    public List<FilterView> getAll() {
        return this.filterRepository.findAllByOrderBySortOrderAsc();
    }

    @Override
    public FilterEditView findOneById(Long id) {
        return this.filterRepository.findOneById(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        this.filterRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Filter create(FilterDto filterDto) {
        Filter newFilter = this.modelMapper.map(filterDto, Filter.class);
        if (newFilter.getSortOrder() == null) {
            newFilter.setSortOrder(0L);
        }
        return this.filterRepository.save(newFilter);
    }

    @Override
    @Transactional
    public Filter update(FilterDto filterDto) {
        Filter filter = this.modelMapper.map(filterDto, Filter.class);
        if (filter.getSortOrder() == null) {
            filter.setSortOrder(0L);
        }
        return this.filterRepository.save(filter);
    }

    @Override
    @Transactional
    public void bulkDelete(List<Long> ids) {
        this.filterRepository.deleteAllByIdIn(ids);
    }
}
