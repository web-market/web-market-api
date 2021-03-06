package market.projection.category;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public interface CategoryItemView {

    String getId();

    String getName();

    Boolean getIsActive();

    Long getSortOrder();

    String getColor();

    @Value("#{target.parentCategory}")
    CategoryDropdownView getParentCategory();

    @Value("#{target.getSubCategories().size() != 0}")
    Boolean getHasSubCategories();

}
