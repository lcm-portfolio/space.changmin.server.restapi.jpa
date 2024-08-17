package space.changmin.server.inquiry;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import space.changmin.server.product.Product;
import space.changmin.server.user.User;

import java.sql.Date;
import java.util.UUID;

/**
 * packageName    : space.changmin.server.inquiry
 * fileName       : Inquiry
 * author         : CMLEE
 * date           : 2024-08-18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-08-18        CMLEE       최초 생성
 */

@Entity
@Table(name = "inquiry")
public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;


    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "is_answered")
    @ColumnDefault("false")
    private boolean isAnswered;

    @Column(name = "is_deleted")
    @ColumnDefault("false")
    private boolean isDeleted;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private User customer;



    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;



    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_id")
    private User answer;


    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;





}
