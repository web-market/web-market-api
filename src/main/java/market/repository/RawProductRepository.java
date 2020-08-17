package market.repository;

import market.entity.RawProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RawProductRepository extends JpaRepository<RawProduct, Long> {

    RawProduct findOneById(Long id);

    void deleteAllByIdIn(List<Long> ids);
}
