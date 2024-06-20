package umc.spring.service.MemberService;

import umc.spring.domain.Member;
import umc.spring.repository.MemberRepository;
import umc.spring.web.dto.MemberRequestDTO;


public interface MemberCommandService {

    Member joinMember(MemberRequestDTO.JoinDto request);

    Member addMission(Long memberId, Long missionId); //✅TODO: 프론트가 mission ID를 알 수 있나? ㅇㅇ 생성마다 id값 넘겨줬잖아
}
