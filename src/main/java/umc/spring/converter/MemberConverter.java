package umc.spring.converter;

import umc.spring.domain.Member;
import umc.spring.domain.enums.Gender;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.MemberResponseDTO;
import umc.spring.web.dto.MissionResponseDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MemberConverter {

    public static MemberResponseDTO.JoinResultDto toJoinResultDTO(Member member) {
        return MemberResponseDTO.JoinResultDto.builder()
                .memberId(member.getId())
                .createdAt(member.getCreateAt())
                .build();
    }

    public static Member toMember(MemberRequestDTO.JoinDto request) {

        Gender gender = null;

        switch (request.getGender()) {
            case 1:
                gender = Gender.MELE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
            case 3:
                gender = Gender.NONE;
                break;
        }

        return Member.builder()
                .address(request.getAddress())
                .birth(request.getBirth())
                .specAddress(request.getSpecAddress())
                .gender(gender)
                .name(request.getName())
                .memberPreferList(new ArrayList<>())
                .build();
    }

    public static MissionResponseDTO.FindMissionsDto toFindMissionDto(Member member, Integer status) {
        List<MissionResponseDTO.FindMissionsDto.MissionsMeta> missions = member.getMemberMissionList().stream()
                .filter(memberMission -> memberMission.getStatus().ordinal() == status)
                .map(membermission -> MissionResponseDTO.FindMissionsDto.MissionsMeta.builder()
                        .storeName(membermission.getMission().getStore().getName())
                        .spec(membermission.getMission().getReward() + " POST_FIX")
                        .status(membermission.getStatus())
                        .reward(membermission.getMission().getReward())
                        .build())
                .collect(Collectors.toList());

        return MissionResponseDTO.FindMissionsDto.builder()
                .missions(missions)
                .build();
    }
}
