package umc.spring.converter;

import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

public class MissionConverter {

    public static MissionResponseDTO.RegisterResultDto toRegisterResult(Mission mission) {
        return MissionResponseDTO.RegisterResultDto.builder()
                .missionId(mission.getId())
                .createAt(mission.getCreateAt())
                .build();
    }

    public static Mission toMission(MissionRequestDTO.RegisterMissionDto mission, Store store) {
        return Mission.builder()
                .deadline(mission.getDeadline())
                .mission_spec(mission.getMissionSpec())
                .reward(mission.getReward())
                .store(store)
                .build();
    }
}
