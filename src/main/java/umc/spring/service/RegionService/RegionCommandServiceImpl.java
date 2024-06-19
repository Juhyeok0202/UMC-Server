package umc.spring.service.RegionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.RegionConverter;
import umc.spring.domain.Region;
import umc.spring.repository.RegionRepository;
import umc.spring.web.dto.RegionRequestDTO;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RegionCommandServiceImpl implements RegionCommandService {

    private final RegionRepository regionRepository;
    @Override
    @Transactional
    public Region registerRegion(RegionRequestDTO.RegisterRegionDto request) {
        Region region = RegionConverter.toRegion(request);
        return regionRepository.save(region);
    }
}
