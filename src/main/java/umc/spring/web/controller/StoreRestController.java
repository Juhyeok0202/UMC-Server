package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.service.StoreService.StoreCommandService;
import umc.spring.validation.annotation.ExistMember;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

import javax.validation.Valid;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreRestController {

    private final StoreCommandService storeCommandService;

    @PostMapping("/{regionId}")
    public ApiResponse<StoreResponseDTO.RegisterResultDto> registerStore(@RequestBody StoreRequestDTO.RegisterStoreDto request,
                                                                         @PathVariable(name = "regionId") Long regionId) {
        Store store = storeCommandService.registerStore(request,regionId);
        return ApiResponse.onSuccess(StoreConverter.toRegisterResultDTO(store));
    }

    @PostMapping("/{storeId}/reviews") // 가게에 리뷰 추가하기 API
    public ApiResponse<StoreResponseDTO.CreateReviewResultDTO> createReview(@RequestBody @Valid StoreRequestDTO.ReviewDto request,
                                                                           @ExistStore @PathVariable(name = "storeId") Long storeId,
                                                                           @ExistMember @RequestParam(name = "memberId") Long memberId) {
        Review review = storeCommandService.createReview(memberId, storeId, request);
        return ApiResponse.onSuccess(StoreConverter.toCreateReviewResultDTO(review));
    }
}
