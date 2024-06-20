package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.Region;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    public static StoreResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review){
        return StoreResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .createdAt(review.getCreateAt().toLocalDate())
                .body(review.getBody())
                .build();
    }
    public static StoreResponseDTO.ReviewPreviewListDTO reviewPreViewListDTO(Page<Review> reviewList){

        List<StoreResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(StoreConverter::reviewPreViewDTO).collect(Collectors.toList());

        return StoreResponseDTO.ReviewPreviewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }

    public static StoreResponseDTO.StoreMissionDTO storeMissionDTO(Mission mission) {
        return StoreResponseDTO.StoreMissionDTO.builder()
                .deadline(mission.getDeadline())
                .mission_spec(mission.getMission_spec())
                .reward(mission.getReward())
                .build();
    }

    public static StoreResponseDTO.StoreMissionListDTO storeMissionListDTO(Page<Mission> missionList) {
        List<StoreResponseDTO.StoreMissionDTO> storeMissionList = missionList.stream()
                .map(StoreConverter::storeMissionDTO).collect(Collectors.toList());

        return StoreResponseDTO.StoreMissionListDTO.builder()
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(storeMissionList.size())
                .missionList(storeMissionList)
                .build();
    }
}
