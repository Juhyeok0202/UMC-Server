package umc.spring.service.MissionService;

import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.web.dto.MissionRequestDTO;

public interface MissionCommanService {

    Mission registerMission(MissionRequestDTO.RegisterMissionDto request, Long storeId);

    Member updateMissionStatus(Long memberId, Long missionId);
}
