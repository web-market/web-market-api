package market.service.impl;

import lombok.RequiredArgsConstructor;
import market.dto.provider.ProviderDto;
import market.entity.Provider;
import market.projection.provider.ProviderItemView;
import market.repository.ProviderRepository;
import market.service.ProviderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProviderServiceImpl implements ProviderService {

    private final ModelMapper modelMapper;
    private final ProviderRepository providerRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ProviderItemView> getProviders() {
        return this.providerRepository.findAllBy();
    }

    @Override
    @Transactional(readOnly = true)
    public ProviderItemView getProvider(Long id) {
        return this.providerRepository.getById(id);
    }

    @Override
    @Transactional
    public Provider create(ProviderDto providerDto) {
        Provider provider = this.modelMapper.map(providerDto, Provider.class);
        return this.providerRepository.save(provider);
    }

    @Override
    @Transactional
    public Provider update(ProviderDto providerDto) {
        Provider provider = this.modelMapper.map(providerDto, Provider.class);
        return this.providerRepository.save(provider);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        this.providerRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void bulkDelete(List<Long> ids) {
        this.providerRepository.deleteAllByIdIn(ids);
    }


}
