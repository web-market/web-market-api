package market.serviceImpl;

import lombok.RequiredArgsConstructor;
import market.dto.category.CategoryDto;
import market.dto.category.CategoryDropdownDto;
import market.dto.category.CategorySideMenuDto;
import market.entity.Category;
import market.repository.CategoryRepository;
import market.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ)
    public List<CategoryDropdownDto> getAll() {
        return this.modelMapper.map(this.categoryRepository.findAll(),
                new TypeToken<List<CategoryDropdownDto>>(){}.getType());
    }

    @Transactional(readOnly = true)
    public List<CategorySideMenuDto> getSideMenu() {
        return this.modelMapper.map(this.categoryRepository.findAll(),
                new TypeToken<List<CategorySideMenuDto>>(){}.getType());
    }

    @Transactional(readOnly = true, isolation = Isolation.REPEATABLE_READ)
    public List<CategoryDto> getAllFull() {
        return this.modelMapper.map(this.categoryRepository.findAll(),
                new TypeToken<List<CategoryDto>>(){}.getType());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Category create(CategoryDto categoryDto) {
        return this.categoryRepository.save(this.modelMapper.map(categoryDto, Category.class));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Category update(CategoryDto categoryDto) {
        return this.categoryRepository.save(modelMapper.map(categoryDto, Category.class));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteCategory(Long id) {
        this.categoryRepository.deleteById(id);
    }

}
