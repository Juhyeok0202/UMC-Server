package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.service.StoreService.StoreCommandService;
import umc.spring.service.StoreService.StoreQueryService;
import umc.spring.validation.annotation.CheckPage;
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
    private final StoreQueryService storeQueryService;

    @PostMapping("/{regionId}")
    public ApiResponse<StoreResponseDTO.RegisterResultDto> registerStore(@RequestBody StoreRequestDTO.RegisterStoreDto request,
                                                                         @PathVariable(name = "regionId") Long regionId) {
        Store store = storeCommandService.registerStore(request, regionId);
        return ApiResponse.onSuccess(StoreConverter.toRegisterResultDTO(store));
    }

    @PostMapping("/{storeId}/reviews") // 가게에 리뷰 추가하기 API
    public ApiResponse<StoreResponseDTO.CreateReviewResultDTO> createReview(@RequestBody @Valid StoreRequestDTO.ReviewDto request,
                                                                            @ExistStore @PathVariable(name = "storeId") Long storeId,
                                                                            @ExistMember @RequestParam(name = "memberId") Long memberId) {
        Review review = storeCommandService.createReview(memberId, storeId, request);
        return ApiResponse.onSuccess(StoreConverter.toCreateReviewResultDTO(review));
    }

    @GetMapping("/{storeId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API", description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!")
    })
    public ApiResponse<StoreResponseDTO.ReviewPreviewListDTO> getReviewList(@ExistStore @PathVariable(name = "storeId") Long storeId,
                                                                            @CheckPage @RequestParam(name = "page") Integer page) {
        Page<Review> reviewList = storeQueryService.getReviewList(storeId, page);
        return ApiResponse.onSuccess(StoreConverter.reviewPreViewListDTO(reviewList));
    }

    @GetMapping("/{storeId}/{memberId}/reviews")//TODO: Security구현되면, member정보 ContextHolder에서 꺼내 쓰면 됨
    public ApiResponse<StoreResponseDTO.ReviewPreviewListDTO> getMyReviewList(@ExistStore @PathVariable(name = "storeId") Long storeId,
                                                                              @ExistMember @PathVariable(name = "memberId") Long memberId,
                                                                              @CheckPage @RequestParam(name = "page") Integer page) {
        Page<Review> myReviewList = storeQueryService.getMyReviewList(storeId, memberId, page);
        return ApiResponse.onSuccess(StoreConverter.reviewPreViewListDTO(myReviewList));
    }

    @GetMapping("/{storeId}/missions")
    public ApiResponse<StoreResponseDTO.StoreMissionListDTO> getMissionList(@ExistStore @PathVariable(name = "storeId") Long storeId,
                                                                            @CheckPage @RequestParam(name = "page") Integer page) {

        //TODO: @CheckPage가 적용이 안됐다. WHY???
        int actualPage = Math.max(1, page) - 1;
        Page<Mission> missionList = storeQueryService.getMissionList(storeId, actualPage);
        return ApiResponse.onSuccess(StoreConverter.storeMissionListDTO(missionList));
    }
}
