package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.MemberHandler;
import umc.spring.apiPayload.exception.handler.StoreHandler;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.MissionRequestDTO;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionCommanServiceImpl implements MissionCommanService{

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Mission registerMission(MissionRequestDTO.RegisterMissionDto request, Long storeId) {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));
        Mission mission = MissionConverter.toMission(request, store);

        return missionRepository.save(mission);
    }

    @Override
    @Transactional
    public Member updateMissionStatus(Long memberId, Long missionId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        member.getMemberMissionList().forEach(
                memberMission -> {
                    if (memberMission.getMission().getId() == missionId) {
                        memberMission.changeStatusToComplete();
                    }
                }
        );
        //TODO: COMPLETE로 변환은 됨. 반환 로직만 잘 작성해주자.
        //아예 바뀐 미션에 대한 참고화면 값들 똑같이 단일JSON만 넘겨주자.
        return member;
    }
}
