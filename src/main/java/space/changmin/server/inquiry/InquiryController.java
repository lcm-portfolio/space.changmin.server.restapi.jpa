package space.changmin.server.inquiry;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import space.changmin.server.annotations.CurrentUserKey;

import java.util.List;
import java.util.UUID;

/**
 * packageName    : space.changmin.server.customer
 * fileName       : CustomerController
 * author         : CMLEE
 * date           : 2024-08-18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-08-18        CMLEE       최초 생성
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inquiry")
public class InquiryController {


    private final InquiryService inquiryService;

    @GetMapping
    public ResponseEntity<?> getMyInquiry(
            @CurrentUserKey UUID customerId,
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            @RequestParam("answered") Boolean answered) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Inquiry> inquiryList = inquiryService.getMyInquiry(pageable, customerId);

        return ResponseEntity.ok(inquiryList);
    }

    @GetMapping("/product")
    public ResponseEntity<?> getInquiryByProduct(
            @RequestParam("productId") UUID productId,
            @RequestParam("page") Integer page,
            @RequestParam("size") Integer size,
            @RequestParam("answered") Boolean answered) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Inquiry> inquiryList = inquiryService.getInquiryByProduct(pageable, productId);



        return null;
    }

    @PostMapping
    public ResponseEntity<InquiryResponseDTO> createInquiry() {
        return null;
    }

    @PostMapping("/answer")
    public ResponseEntity<InquiryResponseDTO> answerInquiry() {
        return null;
    }


}
