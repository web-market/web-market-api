package market.service;

import market.dto.mediaCategory.MediaCategoryDto;
import market.entity.MediaCategory;
import market.projection.mediaCategory.MediaCategoryInlineView;

import java.util.List;

public interface MediaCategoryService {

    List<MediaCategoryInlineView> getAllInline();

    MediaCategory create(MediaCategoryDto mediaCategoryDto);

    MediaCategory update(MediaCategoryDto mediaCategoryDto);

    void delete(Long id);

    void delete(Long id, Boolean deleteChildren);

}
