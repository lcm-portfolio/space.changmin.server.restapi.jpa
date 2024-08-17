package space.changmin.server.review;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * packageName    : space.changmin.server.review
 * fileName       : ReviewRepository
 * author         : CMLEE
 * date           : 2024-08-18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-08-18        CMLEE       최초 생성
 */
public interface ReviewRepository extends JpaRepository<Review, UUID> {
}
