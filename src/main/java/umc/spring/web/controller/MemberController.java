package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MemberConverter;
import umc.spring.domain.Member;
import umc.spring.service.MemberService.MemberCommandService;
import umc.spring.service.MemberService.MemberQueryService;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.MemberResponseDTO;
import umc.spring.web.dto.MissionResponseDTO;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;
    @PostMapping("/")
    public ApiResponse<MemberResponseDTO.JoinResultDto> join(@RequestBody MemberRequestDTO.JoinDto request) {
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    @GetMapping("{memberId}/missions")
    public ApiResponse<MissionResponseDTO.FindMissionsDto> findMissions(
            @PathVariable(name = "memberId") Long memberId, @RequestParam(name = "status") Integer status) {
        MissionResponseDTO.FindMissionsDto missions = memberQueryService.findMissions(memberId, status);
        return ApiResponse.onSuccess(missions);
    }

    @PostMapping("/{memberId}/{missionId}") //가게의 미션을 도전 중인 미션에 추가 API
    public ApiResponse<MemberResponseDTO.JoinResultDto> addMission(
            @PathVariable(name = "memberId") Long memberId,
            @PathVariable(name = "missionId") Long missionId) {
        Member member = memberCommandService.addMission(memberId, missionId);
        return ApiResponse.onSuccess(MemberConverter.toAddResultDTO(member));
    }
}
