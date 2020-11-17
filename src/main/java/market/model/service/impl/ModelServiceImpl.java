package market.model.service.impl;

import lombok.RequiredArgsConstructor;
import market.entity.Model;
import market.model.ModelRepository;
import market.model.dto.ModelDto;
import market.model.dto.ModelView;
import market.model.service.ModelService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ModelView> getModels() {
        return this.modelRepository.findAllBy();
    }

    @Override
    @Transactional(readOnly = true)
    public ModelView getModel(Long id) {
        return this.modelRepository.findOneById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Model getFullModel(Long id) {
        return this.modelRepository.getById(id);
    }

    @Override
    @Transactional
    public Model create(ModelDto modelDto) {
        var model = this.modelMapper.map(modelDto, Model.class);
        return this.modelRepository.save(model);
    }

    @Override
    @Transactional
    public Model update(ModelDto modelDto) {
        var model = this.modelMapper.map(modelDto, Model.class);
        return this.modelRepository.save(model);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        this.modelRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void bulkDelete(List<Long> ids) {
        this.modelRepository.deleteAllByIdIn(ids);
    }

}
