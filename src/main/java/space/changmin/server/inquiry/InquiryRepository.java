package space.changmin.server.inquiry;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * packageName    : space.changmin.server.inquiry
 * fileName       : InquiryRepository
 * author         : CMLEE
 * date           : 2024-08-18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-08-18        CMLEE       최초 생성
 */
public interface InquiryRepository extends JpaRepository<Inquiry, UUID> {

    Page<Inquiry> findAllByCustomerIdOrderByCreatedAtDesc(UUID customerId, Pageable pageable);

    Page<Inquiry> findAllByProductIdOrderByCreatedAtDesc(UUID productId, Pageable pageable);
}
