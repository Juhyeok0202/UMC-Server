package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.domain.enums.MissionStatus;

import java.util.List;

public class MissionResponseDTO {

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class FindMissionsDto{
        private List<MissionsMeta> missions;

        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        @Getter
        public static class MissionsMeta {
            private String storeName;
            private String spec;
            private MissionStatus status;
            private Integer reward;
        }
    }
}
