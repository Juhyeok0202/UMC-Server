package umc.spring.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.spring.apiPayload.code.BaseErrorCode;
import umc.spring.apiPayload.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),


    // 멤버 관련 응답
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER400_1", "사용자가 없습니다."),
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER400_2", "닉네임은 필수 입니다."),

    // 게시글 관련 응답
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "ARTICLE400_1", "게시글이 없습니다."),

    // FOOD_CATAGORY 관련 응답
    FOOD_CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "FOOD_CATEGORY400_1", "음식카테고리가 없습니다."),

    // 미션 관련 응답
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION400_1", "해당 미션은 존재하지 않습니다."),

    // Store관련 응답
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE400_1", "해당 가게는 존재하지 않습니다."),

    // 기타
    PAGE_ONE_OR_MORE(HttpStatus.BAD_REQUEST, "ETC400_1", "Page값은 1 이상이어야 합니다."),

    // TEST용 응답
    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP400_1", "이것은 테스트다!!"),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}
