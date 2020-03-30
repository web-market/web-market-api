package market.repository;

import market.entity.Manufacturer;
import market.projection.manufacturer.ManufacturerView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {

    List<ManufacturerView> findAllBy();

    ManufacturerView getById(Long id);

    void deleteById(Long id);

    void deleteAllByIdIn(List<Long> ids);

}
