package umc.spring.converter;

import umc.spring.domain.Region;
import umc.spring.domain.Store;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

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
}
