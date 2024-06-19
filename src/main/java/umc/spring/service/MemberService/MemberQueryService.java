package umc.spring.service.MemberService;

import umc.spring.web.dto.MissionResponseDTO;

public interface MemberQueryService {

    MissionResponseDTO.FindMissionsDto findMissions(Long memberId, Integer status);
}
