package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.domain.Mission;

import javax.persistence.Lob;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class StoreResponseDTO {

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class RegisterResultDto{
        Long storeId;
        LocalDateTime createdAt;
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class CreateReviewResultDTO {
        private Long reviewId;
        private LocalDateTime createdAt;
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class ReviewPreviewListDTO {
        List<ReviewPreViewDTO> reviewList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewPreViewDTO{
        String ownerNickname;
        Float score;
        String body;
        LocalDate createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StoreMissionListDTO {
        List<StoreMissionDTO> missionList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StoreMissionDTO {
        private Integer reward;
        private LocalDateTime deadline;
        private String mission_spec;
    }


}
