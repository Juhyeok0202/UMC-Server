package umc.spring.web.dto;

import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MemberRequestDTO {

    @Getter
    public static class JoinDto {
        String name;
        Integer gender;
        LocalDate birth;
        String address;
        String specAddress;
        List<Long> preferredFoods = new ArrayList<>(); //
        /*
        List<Long> preferredFoods = new ArrayList<>(); //

        음식 카테고리를 조회하는 API를 호출하고,
        그 API의 결과에서 음식 카테고리의 id값을
        프론트엔드가 넘겨준다는 것을 전제로 한 것입니다!
         */
    }

}
