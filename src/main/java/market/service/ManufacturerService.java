package market.service;

import market.dto.manufacturer.ManufacturerDto;
import market.entity.Manufacturer;
import market.projection.manufacturer.ManufacturerView;

import java.util.List;

public interface ManufacturerService {

    List<ManufacturerView> getAll();

    Manufacturer findOneById(Long id);

    ManufacturerView getOne(Long id);

    Manufacturer create(ManufacturerDto manufacturerDto);

    Manufacturer update(ManufacturerDto manufacturerDto);

    void delete(Long id);

    void bulkDelete(List<Long> ids);

}