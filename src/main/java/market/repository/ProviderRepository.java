package market.repository;

import market.entity.Provider;
import market.projection.provider.ProviderItemView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProviderRepository extends JpaRepository<Provider, Long> {

    //Projections
    List<ProviderItemView> findAllBy();

    ProviderItemView getById(Long id);

    //DTOs
    void deleteAllByIdIn(List<Long> ids);

}
