package space.changmin.server.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import space.changmin.server.product.Product;

import java.util.UUID;

/**
 * packageName    : space.changmin.server.order
 * fileName       : LineOrder
 * author         : CMLEE
 * date           : 2024-08-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-08-17        CMLEE       최초 생성
 */


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "line_orders")
public class LineOrder {

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id")
    private Order order;

    @OneToOne(mappedBy = "id")
    private Product product;

    private int price;

    private int quantity;


}