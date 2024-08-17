package space.changmin.server.inquiry;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * packageName    : space.changmin.server.inquiry
 * fileName       : CustomerService
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
public class InquiryService {


    private final InquiryRepository inquiryRepository;

    public Page<Inquiry> getMyInquiry(Pageable pageable, UUID customerId) {
        Page<Inquiry> page = inquiryRepository.findAllByCustomerIdOrderByCreatedAtDesc(customerId, pageable);
        return page;
    }

    public Page<Inquiry> getInquiryByProduct(Pageable pageable, UUID productId) {
        Page<Inquiry> page = inquiryRepository.findAllByProductIdOrderByCreatedAtDesc(productId, pageable);
        return page;
    }


}
