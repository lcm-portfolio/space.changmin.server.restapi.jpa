package space.changmin.server.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * packageName    : space.changmin.server.cart
 * fileName       : CartService
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
public class CartService {

    private final CartRepository cartRepository;

    public Page<Cart> findAllByUserId(String userId, Pageable pageable) {
        return cartRepository.findAllByUserId(userId, pageable);
    }

    public void save(Cart cart) {
        cartRepository.save(cart);
    }


}
