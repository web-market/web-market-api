package market.service.impl;

import lombok.RequiredArgsConstructor;
import market.dto.uiProduct.UIProductDto;
import market.entity.UIProduct;
import market.repository.UIProductRepository;
import market.service.CategoryService;
import market.service.FilterValueService;
import market.service.ManufacturerService;
import market.service.UIProductService;
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
    private final UIProductRepository UIProductRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<UIProduct> getAll() {
        return this.UIProductRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public UIProduct findOneById(Long id) {
        return this.UIProductRepository.findOneById(id);
    }

    @Override
    @Transactional
    public UIProduct create(UIProductDto UIProductDto) {
        UIProduct newUIProduct = this.modelMapper.map(UIProductDto, UIProduct.class);
//        if (UIProductDto.getCategoryIds() != null) {
//            List<Category> categories = this.categoryService.getByIdIn(UIProductDto.getCategoryIds());
//            newUIProduct.setCategories(categories);
//        }
//        if (UIProductDto.getFilterValueIds() != null) {
//            List<FilterValue> filterValues = this.filterValueService.getByIdIn(UIProductDto.getFilterValueIds());
//            newUIProduct.setFilterValues(filterValues);
//        }
        return this.UIProductRepository.save(newUIProduct);
    }

    @Override
    @Transactional
    public UIProduct update(UIProductDto UIProductDto) {
        UIProduct UIProduct = this.modelMapper.map(UIProductDto, UIProduct.class);
//        if (UIProductDto.getCategoryIds() != null) {
//            List<Category> categories = this.categoryService.getByIdIn(UIProductDto.getCategoryIds());
//            UIProduct.setCategories(categories);
//        }
//        if (UIProductDto.getFilterValueIds() != null) {
//            List<FilterValue> filterValues = this.filterValueService.getByIdIn(UIProductDto.getFilterValueIds());
//            UIProduct.setFilterValues(filterValues);
//        }
        return this.UIProductRepository.save(UIProduct);
    }

    @Override
    @Transactional
    public void bulkDelete(List<Long> ids) {
        this.UIProductRepository.deleteAllByIdIn(ids);
    }

//    @Override
//    public List<UIProduct> getAllByCategories(List<Long> ids) {
//        return this.UIProductRepository.getAllByCategoriesIdIn(ids);
//    }

    @Override
    public List<UIProduct> getAllByNameLike(String name) {
        return this.UIProductRepository.getAllByNameContainingIgnoreCase(name);
    }

}
