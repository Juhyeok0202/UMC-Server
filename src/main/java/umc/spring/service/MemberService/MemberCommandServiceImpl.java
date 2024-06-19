package umc.spring.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.FoodCategoryHandler;
import umc.spring.apiPayload.exception.handler.MemberHandler;
import umc.spring.apiPayload.exception.handler.MissionHandler;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.converter.MemberPreferConverter;
import umc.spring.domain.FoodCategory;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.domain.mapping.MemberPrefer;
import umc.spring.repository.FoodCategoryRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.MissionRepository;
import umc.spring.service.MemberMissionRepository;
import umc.spring.web.dto.MemberRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final MissionRepository missionRepository;
    @Override
    @Transactional
    public Member joinMember(MemberRequestDTO.JoinDto request) {

        // Member 포멧으로 변환
        Member newMember = MemberConverter.toMember(request);

        // 요청받은 Category PK number DB에 존재 확인 및 FoodCategory타입으로 가져오기
        List<FoodCategory> foodCategoryList = request.getPreferredFoods().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());

        // FoodCategory -> MemberPrefer 로 변환 후, Member와 연관관계 양방향으로 저장
        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);
        memberPreferList.forEach(memberPrefer -> {
            memberPrefer.setMember(newMember);
        });

        // save member in DB
        return memberRepository.save(newMember);
    }

    @Override
    @Transactional
    public Member addMission(Long memberId, Long missionId) { //TODO: 더 좋은 방법이 있을 것 같은데..
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Mission mission = missionRepository.findById(missionId).orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));

        // 연관관계 양방향으로 저장
        MemberMission memberMission = MemberMissionConverter.toMemberMission(mission);
        memberMission.setMember(member); //❌TODO : 이 메서드가 제대로 동작 안함; 달ㄴ API로 호출하면 List<MemberMission>이 없음
        memberMission.setMission(mission);

        return member;
    }
}
