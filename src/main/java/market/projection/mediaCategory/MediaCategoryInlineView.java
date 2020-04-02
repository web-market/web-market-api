package market.projection.mediaCategory;

import org.springframework.beans.factory.annotation.Value;

public interface MediaCategoryInlineView {

    String getId();

    String getName();

    @Value("#{target.parentCategory}")
    MediaCategoryDropdownView getParentCategory();
}
