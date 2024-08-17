package space.changmin.server.lineorder;

import lombok.Data;

/**
 * packageName    : space.changmin.server.order
 * fileName       : LineOrderDTO
 * author         : CMLEE
 * date           : 2024-08-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-08-17        CMLEE       최초 생성
 */

@Data
public class LineOrderDTO {

        private String productId;

        private int quantity;

        private int price;;


        public LineOrder toEntity() {
            return LineOrder.builder()
                    .quantity(quantity)
                    .price(price)
                    .build();
        }
}
