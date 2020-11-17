package market.model;

import market.entity.Model;
import market.model.dto.ModelView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModelRepository extends JpaRepository<Model, Long> {

    //Projections
    List<ModelView> findAllBy();

    ModelView findOneById(Long id);

    //DTOs
    Model getById(Long id);

    void deleteById(Long id);

    void deleteAllByIdIn(List<Long> ids);
}
