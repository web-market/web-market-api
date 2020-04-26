package market.serviceImpl;

import lombok.RequiredArgsConstructor;
import market.dto.mediaCategory.MediaCategoryDto;
import market.entity.MediaCategory;
import market.projection.mediaCategory.MediaCategoryInlineView;
import market.repository.MediaCategoryRepository;
import market.service.MediaCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MediaCategoryServiceImpl implements MediaCategoryService {

    private final ModelMapper modelMapper;
    private final MediaCategoryRepository mediaCategoryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<MediaCategoryInlineView> getAllInline() {
        return this.mediaCategoryRepository.findAllBy();
    }

    @Override
    @Transactional(readOnly = true)
    public MediaCategory getById(Long id) {
        return this.mediaCategoryRepository.getById(id);
    }

    @Override
    @Transactional
    public MediaCategory create(MediaCategoryDto mediaCategoryDto) {
        MediaCategory newCategory = this.modelMapper.map(mediaCategoryDto, MediaCategory.class);
        if (mediaCategoryDto.getParentCategoryId() != null) {
            MediaCategory parentCategory = this.mediaCategoryRepository.getById(mediaCategoryDto.getParentCategoryId());
            newCategory.setParentCategory(parentCategory);
        }
        return this.mediaCategoryRepository.save(newCategory);
    }

    @Override
    @Transactional
    public MediaCategory update(MediaCategoryDto mediaCategoryDto) {
        MediaCategory mediaCategory = modelMapper.map(mediaCategoryDto, MediaCategory.class);
        mediaCategory.setParentCategory(this.mediaCategoryRepository.getById(mediaCategoryDto.getParentCategoryId()));
        return this.mediaCategoryRepository.save(mediaCategory);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        this.mediaCategoryRepository.deleteById(id);
    }


    @Override
    public void delete(Long id, Boolean deleteChildren) {

    }
}
