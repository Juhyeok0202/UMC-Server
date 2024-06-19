package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Store;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.StoreRequestDTO;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public Store registerStore(StoreRequestDTO.RegisterStoreDto request) {
        Store store = StoreConverter.toStore(request, null);

        return storeRepository.save(store);
    }
}
