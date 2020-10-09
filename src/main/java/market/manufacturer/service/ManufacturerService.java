package market.manufacturer.service;

import market.manufacturer.dto.ManufacturerDto;
import market.entity.Manufacturer;
import market.manufacturer.dto.ManufacturerView;

import java.util.List;

public interface ManufacturerService {

    //Projections
    List<ManufacturerView> getManufacturers();

    ManufacturerView getManufacturer(Long id);

    //DTOs
    Manufacturer getFullManufacturer(Long id);

    Manufacturer create(ManufacturerDto manufacturerDto);

    Manufacturer update(ManufacturerDto manufacturerDto);

    void delete(Long id);

    void bulkDelete(List<Long> ids);

}