package market.category.dto;

public interface CategoryEditView {

    Long getId();

    String getName();

    CategoryDropdownView getParentCategory();

    Long getSortOrder();

    String getColor();

    Boolean getIsActive();

}
