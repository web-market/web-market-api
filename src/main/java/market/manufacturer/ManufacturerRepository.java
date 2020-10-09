package market.manufacturer;

import market.entity.Manufacturer;
import market.manufacturer.dto.ManufacturerView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {

    //Projections
    List<ManufacturerView> findAllBy();

    ManufacturerView findOneById(Long id);

    //DTOs
    Manufacturer getById(Long id);

    void deleteById(Long id);

    void deleteAllByIdIn(List<Long> ids);

}
