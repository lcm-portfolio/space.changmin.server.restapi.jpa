package space.changmin.server.cart;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * packageName    : space.changmin.server.cart
 * fileName       : CartRepository
 * author         : CMLEE
 * date           : 2024-08-18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-08-18        CMLEE       최초 생성
 */


public interface CartRepository extends JpaRepository<Cart, UUID> {
    Page<Cart> findAllByUserId(String userId, Pageable pageable);

}
