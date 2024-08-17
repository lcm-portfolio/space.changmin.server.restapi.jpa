package space.changmin.server.delivery;

import jakarta.persistence.*;

import java.util.UUID;

/**
 * packageName    : space.changmin.server.delivery
 * fileName       : Delivery
 * author         : CMLEE
 * date           : 2024-08-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-08-17        CMLEE       최초 생성
 */

@Entity
@Table (name = "delivery")
public class Delivery {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.UUID)
    private UUID id;

    private String address;

    private String phoneNumber;


    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

}
