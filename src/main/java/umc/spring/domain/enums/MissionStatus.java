package umc.spring.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MissionStatus {
    CHALLENGING(0), COMPLETE(1);

    Integer status;
}
