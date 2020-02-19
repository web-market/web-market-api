package market.serviceImpl;

import lombok.RequiredArgsConstructor;
import market.entity.Filter;
import market.repository.FilterRepository;
import market.service.FilterService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilterServiceImpl implements FilterService {

    private final FilterRepository filterRepository;

    @Override
    public List<Filter> getAll() {
        return this.filterRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        this.filterRepository.deleteById(id);
    }
}
