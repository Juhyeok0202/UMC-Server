package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


public class MemberResponseDTO {

    @AllArgsConstructor @NoArgsConstructor
    @Builder
    @Getter
    public static class JoinResultDto {
        Long memberId;
        LocalDateTime createdAt;
    }
}
