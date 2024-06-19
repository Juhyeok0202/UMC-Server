package umc.spring.service.MemberService;

import umc.spring.domain.Member;
import umc.spring.web.dto.MissionResponseDTO;

import java.util.Optional;

public interface MemberQueryService {

    MissionResponseDTO.FindMissionsDto findMissions(Long memberId, Integer status);

    Optional<Member> findMember(Long id);
}
