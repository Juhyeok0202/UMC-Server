package umc.spring.converter;

import umc.spring.domain.Region;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

import java.time.LocalDateTime;

public class StoreConverter {

    public static StoreResponseDTO.RegisterResultDto toRegisterResultDTO(Store store) {

        return StoreResponseDTO.RegisterResultDto.builder()
                .createdAt(store.getCreateAt())
                .storeId(store.getId())
                .build();
    }

    public static Store toStore(StoreRequestDTO.RegisterStoreDto request, Region region) {


        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .region(region)
                .build();
    }

    public static Review toReview(StoreRequestDTO.ReviewDto request) {
        return Review.builder()
                .score(request.getScore())
                .body(request.getBody())
                .title(request.getTitle())
                .build();
    }

    public static StoreResponseDTO.CreateReviewResultDTO toCreateReviewResultDTO(Review review){
        return StoreResponseDTO.CreateReviewResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
