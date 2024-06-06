package umc.spring.apiPayload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.spring.apiPayload.code.BaseCode;
import umc.spring.apiPayload.code.status.SuccessStatus;

/**
 *
 * @param <T>
 * {
 * 	isSuccess : Boolean
 * 	code : String
 * 	message : String
 * 	result : {응답으로 필요한 또 다른 json}
 * }
 *
 * [응답 형태]
 * {
 * 	"isSuccess ": true,
 * 	"code" : "2000",
 * 	"message" : "OK",
 * 	"result" :
 *                {
 * 			"testString" : "This is test!"
 *        }
 * }
 */
@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class ApiResponse<T> { // 어떤 형태의 값이 올지 모르기에 Generic으로 클래스 생성

    @JsonProperty("isSuccess")
    private final Boolean isSuccess;
    private final String code; // HTTP 상태 코드 외에 더 세부적인 결과를 알려주기 위해 사용
    private final String message; // code에 추가적으로 어떤 결과인지 알려주기 위해 사용
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result; // 실패할 경우 null(아닌 케이스도 존재)

    // 성공한 경우 응답 생성
    public static <T> ApiResponse<T> onSuccess(T result) {
        return new ApiResponse<>(true, SuccessStatus._OK.getCode(), SuccessStatus._OK.getMessage(), result);
    }

    public static <T> ApiResponse<T> of(BaseCode code, T result) {
        return new ApiResponse<>(true, code.getReasonHttpStatus().getCode(), code.getReasonHttpStatus().getMessage(), result);
    }

    // 실패한 경우 응답 생성
    public static <T> ApiResponse<T> onFailure(String code, String message, T data) {
        return new ApiResponse<>(true, code, message, data);
    }
}
