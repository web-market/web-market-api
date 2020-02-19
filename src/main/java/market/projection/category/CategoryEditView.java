package market.projection.category;

public interface CategoryEditView {

    Long getId();

    String getName();

    CategoryDropdownView getParentCategory();

    Long getSortOrder();

    String getColor();

    Boolean getIsActive();

}
