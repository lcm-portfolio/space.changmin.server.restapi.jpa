package space.changmin.server.inquiry;

import lombok.Data;

import java.util.List;

/**
 * packageName    : space.changmin.server.inquiry
 * fileName       : InquiryResponseDTO
 * author         : CMLEE
 * date           : 2024-08-18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-08-18        CMLEE       최초 생성
 */

@Data
public class InquiryResponseDTO {

    List<InquiryDTO> inquiryList;

}
