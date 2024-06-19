package umc.spring.converter;

import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;

public class MemberMissionConverter {

    public static MemberMission toMemberMission(Mission mission) {
        return MemberMission.builder()
                .mission(mission)
                .status(MissionStatus.CHALLENGING)
                .build();
    }
}
