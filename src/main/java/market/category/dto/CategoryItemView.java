package market.category.dto;

import org.springframework.beans.factory.annotation.Value;

public interface CategoryItemView {

    Long getId();

    String getName();

    Boolean getIsActive();

    Long getSortOrder();

    String getColor();

    @Value("#{target.parentCategory == null ? null : target.parentCategory.getId()}")
    Long getParentCategoryId();

    @Value("#{target.getSubCategories().size() != 0}")
    Boolean getHasSubCategories();

}
