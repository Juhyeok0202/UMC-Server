package umc.spring.web.dto;

import java.time.LocalDateTime;

public class MissionRequestDTO {

    public static class registerMissionDto{
        private LocalDateTime deadline;
        private String missionSpec;
        private Integer reward;
    }
}
