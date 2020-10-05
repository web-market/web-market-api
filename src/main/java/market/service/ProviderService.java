package market.service;

import market.dto.provider.ProviderDto;
import market.entity.Provider;
import market.projection.provider.ProviderItemView;

import java.util.List;

public interface ProviderService {

    //Projections
    List<ProviderItemView> getProviders();

    ProviderItemView getProvider(Long id);

    //DTOs
    Provider create(ProviderDto providerDto);

    Provider update(ProviderDto providerDto);

    void delete(Long id);

    void bulkDelete(List<Long> ids);


}
