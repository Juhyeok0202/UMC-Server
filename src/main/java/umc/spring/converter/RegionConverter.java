package umc.spring.converter;

import umc.spring.domain.Region;
import umc.spring.web.dto.RegionRequestDTO;
import umc.spring.web.dto.RegionResponseDTO;

public class RegionConverter {


    public static RegionResponseDTO.registerResultDto toRegisterResultDto(Region region) {
        return RegionResponseDTO.registerResultDto.builder()
                .regionId(region.getId())
                .createdAt(region.getCreateAt())
                .build();
    }
    public static Region toRegion(RegionRequestDTO.RegisterRegionDto request) {
        return Region.builder()
                .name(request.getName())
                .build();
    }
}
