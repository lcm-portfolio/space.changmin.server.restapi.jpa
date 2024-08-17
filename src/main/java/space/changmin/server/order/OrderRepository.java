package space.changmin.server.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * packageName    : space.changmin.server.order
 * fileName       : OrderRepository
 * author         : CMLEE
 * date           : 2024-08-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-08-17        CMLEE       최초 생성
 */
public interface OrderRepository extends JpaRepository<Order, UUID> {


    Page<Order> findAllByUserId(UUID userId, Pageable pageable);

}
