package space.changmin.server.user;

import jakarta.persistence.*;
import org.springframework.security.core.userdetails.UserDetails;
import space.changmin.server.authentication.UserDetailsImpl;

import java.util.UUID;

/**
 * packageName    : space.changmin.server.user
 * fileName       : User
 * author         : CMLEE
 * date           : 2024-08-18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-08-18        CMLEE       최초 생성
 */

@Entity
@Table(name = "users")
public class User {

    @Id
    private String id;

    @Column(unique = true)
    private String username;



    public UserDetails toUserDetails() {
        return UserDetailsImpl.builder()
                .userKey(id)
                .username(username)
                .build();
    }

    @PrePersist
    public void prePersist() {
        this.id = UUID.randomUUID().toString();
    }



}
