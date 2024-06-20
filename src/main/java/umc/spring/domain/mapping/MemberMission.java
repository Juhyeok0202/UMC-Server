package umc.spring.domain.mapping;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.common.BaseTimeEntity;
import umc.spring.domain.enums.MissionStatus;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@DynamicUpdate
@DynamicInsert

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
//    @ColumnDefault("0")
    private MissionStatus status;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    //====연관관계 편의 메서드====//
    public void setMember(Member member) {
        if (this.member != null) member.getMemberMissionList().remove(this);
        this.member = member;
        member.getMemberMissionList().add(this);
    }

    public void setMission(Mission mission) {
        if (this.mission != null) mission.getMemberMissionList().remove(this);
        this.mission = mission;
        mission.getMemberMissionList().add(this);
    }

    //====보조 메서드====//
    public void changeStatusToComplete() {
        this.status = MissionStatus.COMPLETE;
    }
}
