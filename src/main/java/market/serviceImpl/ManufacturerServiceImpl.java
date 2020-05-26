package market.serviceImpl;

import lombok.RequiredArgsConstructor;
import market.dto.manufacturer.ManufacturerDto;
import market.entity.Manufacturer;
import market.projection.manufacturer.ManufacturerView;
import market.repository.ManufacturerRepository;
import market.service.ManufacturerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ManufacturerView> getAll() {
        return this.manufacturerRepository.findAllBy();
    }

    @Override
    public Manufacturer findOneById(Long id) {
        return this.manufacturerRepository.findOneById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public ManufacturerView getOne(Long id) {
        return this.manufacturerRepository.getById(id);
    }

    @Override
    @Transactional
    public Manufacturer create(ManufacturerDto manufacturerDto) {
        Manufacturer manufacturer = this.modelMapper.map(manufacturerDto, Manufacturer.class);
        return this.manufacturerRepository.save(manufacturer);
    }

    @Override
    @Transactional
    public Manufacturer update(ManufacturerDto manufacturerDto) {
        Manufacturer manufacturer = this.modelMapper.map(manufacturerDto, Manufacturer.class);
        return this.manufacturerRepository.save(manufacturer);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        this.manufacturerRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void bulkDelete(List<Long> ids) {
        this.manufacturerRepository.deleteAllByIdIn(ids);
    }
}