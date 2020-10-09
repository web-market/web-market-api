package market.provider.service;

import market.provider.dto.ProviderDto;
import market.entity.Provider;
import market.provider.dto.ProviderItemView;

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
