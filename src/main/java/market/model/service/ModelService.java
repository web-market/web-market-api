package market.model.service;

import market.entity.Model;
import market.model.dto.ModelDto;
import market.model.dto.ModelView;
import java.util.List;

public interface ModelService {

    //Projections
    List<ModelView> getModels();

    ModelView getModel(Long id);

    //DTOs
    Model getFullModel(Long id);

    Model create(ModelDto modelDto);

    Model update(ModelDto modelDto);

    void delete(Long id);

    void bulkDelete(List<Long> ids);


}
