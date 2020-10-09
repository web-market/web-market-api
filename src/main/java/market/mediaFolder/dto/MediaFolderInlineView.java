package market.mediaFolder.dto;

import org.springframework.beans.factory.annotation.Value;

public interface MediaFolderInlineView {

    Long getId();

    String getName();

    @Value("#{target.parentFolder == null ? null : target.parentFolder.getId()}")
    Long getParentFolderId();
}
