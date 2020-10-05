package market.repository;

import market.entity.Supply;
import market.projection.supply.SupplyItemView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplyRepository extends JpaRepository<Supply, Long> {

    //Projections
    List<SupplyItemView> findAllBy();

    SupplyItemView getById(Long id);
}
