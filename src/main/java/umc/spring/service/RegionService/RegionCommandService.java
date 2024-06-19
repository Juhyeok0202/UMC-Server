package umc.spring.service.RegionService;

import umc.spring.domain.Region;
import umc.spring.web.dto.RegionRequestDTO;

public interface RegionCommandService {

    Region registerRegion(RegionRequestDTO.RegisterRegionDto request);
}
