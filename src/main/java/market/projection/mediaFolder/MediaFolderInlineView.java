package market.projection.mediaFolder;

import org.springframework.beans.factory.annotation.Value;

public interface MediaFolderInlineView {

    String getId();

    String getName();

    @Value("#{target.parentFolder == null ? null : target.parentFolder.getId()}")
    Long getParentFolderId();
}
