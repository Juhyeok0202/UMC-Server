package umc.spring.web.dto;

import lombok.Getter;
import umc.spring.validation.annotation.ExistCategories;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MemberRequestDTO {

    @Getter
    public static class JoinDto {
        @NotBlank
        String name;
        @NotNull
        Integer gender;
        @NotNull
        LocalDate birth;
        @Size(min = 5, max = 12)
        String address;
        @Size(min = 5, max = 12)
        String specAddress;
        @ExistCategories
        List<Long> preferredFoods = new ArrayList<>(); //
        /*
        List<Long> preferredFoods = new ArrayList<>(); //

        음식 카테고리를 조회하는 API를 호출하고,
        그 API의 결과에서 음식 카테고리의 id값을
        프론트엔드가 넘겨준다는 것을 전제로 한 것입니다!
         */
    }

}
