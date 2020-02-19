package market.serviceImpl;

import lombok.RequiredArgsConstructor;
import market.repository.ImageRepository;
import market.service.ImageService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

}
