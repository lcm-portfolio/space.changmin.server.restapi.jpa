package space.changmin.server.address;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * packageName    : space.changmin.server.address
 * fileName       : AddressService
 * author         : CMLEE
 * date           : 2024-08-18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-08-18        CMLEE       최초 생성
 */

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public Page<Address> getAddress(String userId, Pageable pageable) {
        return addressRepository.findAllByUserId(userId, pageable);
    }


}
