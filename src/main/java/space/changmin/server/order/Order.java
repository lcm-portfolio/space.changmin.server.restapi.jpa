package space.changmin.server.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import space.changmin.server.delivery.Delivery;
import space.changmin.server.lineorder.LineOrder;
import space.changmin.server.user.User;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

/**
 * packageName    : space.changmin.server.order
 * fileName       : Order
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
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

    @Column(name = "total_price")
    private int totalPrice;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private List<LineOrder> lineOrders;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "order_date")
    @CreationTimestamp
    private Date orderDate;


    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private List<Delivery> deliveries;




}
