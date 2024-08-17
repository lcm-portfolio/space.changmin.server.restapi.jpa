package space.changmin.server.address;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.changmin.server.annotations.CurrentUserKey;

/**
 * packageName    : space.changmin.server.address
 * fileName       : AddressController
 * author         : CMLEE
 * date           : 2024-08-18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-08-18        CMLEE       최초 생성
 */

@RestController
@RequestMapping("/api/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping
    public ResponseEntity<?> getAddress(@CurrentUserKey String userId) {

        Pageable pageable = Pageable.unpaged();

        Page<Address> address = addressService.getAddress(userId, pageable);

        return ResponseEntity.ok(address);

    }


}
