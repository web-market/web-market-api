package market.manufacturer.service.impl;

import lombok.RequiredArgsConstructor;
import market.manufacturer.dto.ManufacturerDto;
import market.entity.Manufacturer;
import market.manufacturer.dto.ManufacturerView;
import market.manufacturer.ManufacturerRepository;
import market.manufacturer.service.ManufacturerService;
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
    public List<ManufacturerView> getManufacturers() {
        return this.manufacturerRepository.findAllBy();
    }

    @Override
    @Transactional(readOnly = true)
    public ManufacturerView getManufacturer(Long id) {
        return this.manufacturerRepository.findOneById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Manufacturer getFullManufacturer(Long id) {
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