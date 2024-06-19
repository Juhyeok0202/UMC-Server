package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

public class RegionRequestDTO {

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Getter
    public static class RegisterRegionDto {
        @NotBlank
        private String name;
    }

}
