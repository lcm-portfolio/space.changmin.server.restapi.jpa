package space.changmin.server.lineorder;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * packageName    : space.changmin.server.order
 * fileName       : LineOrderRepository
 * author         : CMLEE
 * date           : 2024-08-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-08-17        CMLEE       최초 생성
 */
public interface LineOrderRepository extends JpaRepository<LineOrder, UUID> {
}
