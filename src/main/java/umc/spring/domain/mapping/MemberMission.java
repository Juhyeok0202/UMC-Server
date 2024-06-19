package umc.spring.domain.mapping;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.common.BaseTimeEntity;
import umc.spring.domain.enums.MissionStatus;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class MemberMission extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Enumerated(STRING)
    private MissionStatus status;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    //====연관관계 편의 메서드====//
    public void setMember(Member member) {
        this.member = member;
        member.getMemberMissionList().add(this);
    }

    public void setMission(Mission mission) {
        this.mission = mission;
        mission.getMemberMissionList().add(this);
    }
}
