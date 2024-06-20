package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.service.MissionService.MissionCommanService;
import umc.spring.validation.annotation.ExistMember;
import umc.spring.validation.annotation.ExistMission;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

@RestController
@RequestMapping("/missions")
@RequiredArgsConstructor
public class MissionRestController {

    private final MissionCommanService missionCommanService;

    @PostMapping("/{storeId}") //가게에 미션 추가하기 API
    public ApiResponse<MissionResponseDTO.RegisterResultDto> registerMission(
            @PathVariable(name = "storeId") Long storeId,
            @RequestBody MissionRequestDTO.RegisterMissionDto request) {

        Mission mission = missionCommanService.registerMission(request, storeId);
        return ApiResponse.onSuccess(MissionConverter.toRegisterResult(mission));
    }

    @PatchMapping("/{memberId}/{missionId}")
    public ApiResponse<MissionResponseDTO.UpdateMissionStatusDto> updateMissionStatus(@ExistMember @PathVariable(name = "memberId") Long memberId,
                                                                                      @ExistMission @PathVariable(name = "missionId") Long missionId) {
        Member member = missionCommanService.updateMissionStatus(memberId, missionId);
        return ApiResponse.onSuccess(MissionConverter.updateMissionStatusDto(member));
    }
}
