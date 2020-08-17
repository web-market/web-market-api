package market.repository;

import market.entity.Manufacturer;
import market.projection.manufacturer.ManufacturerView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {

    //Projections
    List<ManufacturerView> findAllBy();

    ManufacturerView getById(Long id);

    //DTOs
    Manufacturer findOneById(Long id);

    void deleteById(Long id);

    void deleteAllByIdIn(List<Long> ids);

}
