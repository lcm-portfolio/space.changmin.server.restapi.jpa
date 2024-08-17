package space.changmin.server.review;

import jakarta.persistence.*;
import space.changmin.server.user.User;

import java.util.UUID;

/**
 * packageName    : space.changmin.server.review
 * fileName       : Review
 * author         : CMLEE
 * date           : 2024-08-18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-08-18        CMLEE       최초 생성
 */
@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    private String content;

    private int score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
