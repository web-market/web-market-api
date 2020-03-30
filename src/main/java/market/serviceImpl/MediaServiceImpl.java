package market.serviceImpl;

import lombok.RequiredArgsConstructor;
import market.repository.MediaRepository;
import market.service.MediaService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {

    private final MediaRepository mediaRepository;



}
