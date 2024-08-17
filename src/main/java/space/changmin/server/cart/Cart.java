package space.changmin.server.cart;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import space.changmin.server.product.Product;
import space.changmin.server.user.User;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * packageName    : space.changmin.server.cart
 * fileName       : Cart
 * author         : CMLEE
 * date           : 2024-08-18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-08-18        CMLEE       최초 생성
 */

@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;
}
