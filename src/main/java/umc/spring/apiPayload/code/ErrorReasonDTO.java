package umc.spring.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Builder
@Getter
public class ErrorReasonDTO {

    private final String message;
    private final String code;
    private final Boolean isSuccess;
    private final HttpStatus httpStatus;

    public Boolean getSuccess() {
        return isSuccess;
    }
}
