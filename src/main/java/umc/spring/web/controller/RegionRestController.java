package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.RegionConverter;
import umc.spring.domain.Region;
import umc.spring.service.RegionService.RegionCommandService;
import umc.spring.web.dto.RegionRequestDTO;
import umc.spring.web.dto.RegionResponseDTO;

import javax.validation.Valid;

@RestController
@RequestMapping("/regions")
@RequiredArgsConstructor
public class RegionRestController {

    private final RegionCommandService regionCommandService;
    @PostMapping("")
    public ApiResponse<RegionResponseDTO.registerResultDto> registerRegion(@RequestBody @Valid RegionRequestDTO.RegisterRegionDto request) {
        Region region = regionCommandService.registerRegion(request);
        return ApiResponse.onSuccess(RegionConverter.toRegisterResultDto(region));
    }
}
