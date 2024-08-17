package space.changmin.server.product;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.UUID;

/**
 * packageName    : space.changmin.server.product
 * fileName       : Product
 * author         : CMLEE
 * date           : 2024-08-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-08-17        CMLEE       최초 생성
 */

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String description;
    private int price;

    @Column(name = "category_id")
    private UUID categoryId;

    @Column(name = "seller_id")
    private UUID sellerId;

    @Column(name = "image")
    private String imageUrl;



    @Column(name = "stock_quantity")
    @ColumnDefault("0")
    private int stockQuantity;

    @Column(name = "is_visible")
    @ColumnDefault("true")
    private boolean isVisible;


    @Column(name = "is_deleted")
    @ColumnDefault("false")
    private boolean isDeleted;



}
