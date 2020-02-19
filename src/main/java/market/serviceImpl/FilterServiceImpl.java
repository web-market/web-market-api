package market.serviceImpl;

import lombok.RequiredArgsConstructor;
import market.dto.filter.FilterDto;
import market.entity.Filter;
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
    public List<Filter> getAll() {
        return this.filterRepository.findAll();
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
}
