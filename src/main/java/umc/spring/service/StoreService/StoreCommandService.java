package umc.spring.service.StoreService;

import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.StoreRequestDTO;

public interface StoreCommandService {

    Store registerStore(StoreRequestDTO.RegisterStoreDto request, Long regionId);

    Review createReview(Long memberId, Long storeId, StoreRequestDTO.ReviewDto request);
}
