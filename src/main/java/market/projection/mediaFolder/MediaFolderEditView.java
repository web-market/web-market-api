package market.projection.mediaFolder;

import market.projection.category.CategoryDropdownView;

public interface MediaFolderEditView {

    Long getId();

    String getName();

    MediaFolderDropdownView getParentFolder();

}
