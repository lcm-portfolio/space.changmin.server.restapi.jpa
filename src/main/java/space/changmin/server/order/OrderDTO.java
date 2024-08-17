package space.changmin.server.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import space.changmin.server.lineorder.LineOrderDTO;
import space.changmin.server.user.User;

import java.util.List;

/**
 * packageName    : space.changmin.server.order
 * fileName       : OrderDTO
 * author         : CMLEE
 * date           : 2024-08-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-08-17        CMLEE       최초 생성
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private User userId;

    private List<LineOrderDTO> lineOrders;

    public Order toEntity() {
        return Order.builder()
                .userId(userId)
                .lineOrders(lineOrders.stream().map(LineOrderDTO::toEntity).toList())
                .build();
    }


}
