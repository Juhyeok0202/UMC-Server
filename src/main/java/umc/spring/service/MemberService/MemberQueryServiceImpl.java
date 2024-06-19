package umc.spring.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.MemberHandler;
import umc.spring.converter.MemberConverter;
import umc.spring.domain.Member;
import umc.spring.repository.MemberRepository;
import umc.spring.web.dto.MissionResponseDTO;
import umc.spring.web.dto.MissionResponseDTO.FindMissionsDto.MissionsMeta;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository memberRepository;
    private final String POST_FIX = "원 이상의 식사를 하세요!";
    @Override
    public MissionResponseDTO.FindMissionsDto findMissions(Long memberId, Integer status) {

        Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        return MemberConverter.toFindMissionDto(member, status);
    }
}
