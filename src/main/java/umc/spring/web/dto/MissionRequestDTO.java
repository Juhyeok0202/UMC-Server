package umc.spring.web.dto;

import lombok.Getter;

import java.time.LocalDateTime;

public class MissionRequestDTO {

    @Getter
    public static class RegisterMissionDto{
        private LocalDateTime deadline;
        private String missionSpec;
        private Integer reward;
    }
}
