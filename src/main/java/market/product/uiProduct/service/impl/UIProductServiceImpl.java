package market.product.uiProduct.service.impl;

import lombok.RequiredArgsConstructor;
import market.category.service.CategoryService;
import market.entity.UIProduct;
import market.filterValue.service.FilterValueService;
import market.manufacturer.service.ManufacturerService;
import market.product.uiProduct.service.UIProductService;
import market.product.uiProduct.dto.UIProductDto;
import market.product.uiProduct.dto.UIProductView;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UIProductServiceImpl implements UIProductService {

    private final CategoryService categoryService;
    private final FilterValueService filterValueService;
    private final ManufacturerService manufacturerService;
    private final market.product.uiProduct.UIProductRepository UIProductRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<UIProductView> getAllUIProductsByNameLike(String name) {
        return this.UIProductRepository.findAllByNameContainingIgnoreCase(name);
    }

    //TODO: find out why propagation is used here
    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<UIProductView> getAllUIProducts() {
        return this.UIProductRepository.findAllBy();
    }

//    @Override
//    public List<UIProduct> getAllByCategories(List<Long> ids) {
//        return this.UIProductRepository.getAllByCategoriesIdIn(ids);
//    }

    @Override
    @Transactional(readOnly = true)
    public UIProductView getUIProduct(Long id) {
        return this.UIProductRepository.findOneById(id);
    }

    //TODO: finalize this method
    @Override
    @Transactional
    public UIProduct create(UIProductDto UIProductDto) {
        UIProduct newUIProduct = this.modelMapper.map(UIProductDto, UIProduct.class);
//        if (UIProductDto.getCategoryIds() != null) {
//            List<Category> categories = this.categoryService.getByIdIn(UIProductDto.getCategoryIds());
//            newUIProduct.setCategories(categories);
//        }
        return this.UIProductRepository.save(newUIProduct);
    }

    //TODO: finalize this method
    @Override
    @Transactional
    public UIProduct update(UIProductDto UIProductDto) {
        UIProduct UIProduct = this.modelMapper.map(UIProductDto, UIProduct.class);
//        if (UIProductDto.getCategoryIds() != null) {
//            List<Category> categories = this.categoryService.getByIdIn(UIProductDto.getCategoryIds());
//            UIProduct.setCategories(categories);
//        }
        return this.UIProductRepository.save(UIProduct);
    }

    @Override
    @Transactional
    public void bulkDelete(List<Long> ids) {
        this.UIProductRepository.deleteAllByIdIn(ids);
    }

}