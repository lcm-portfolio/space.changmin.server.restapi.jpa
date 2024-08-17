package space.changmin.server.authentication;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * packageName    : space.changmin.server.authentication
 * fileName       : User
 * author         : CMLEE
 * date           : 2024-08-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-08-17        CMLEE       최초 생성
 */

@Entity
@Table (name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userKey;

    private String username;
    private String password;
    private String email;
    private String name;
    private String address;
    private String phone;



    @Override
    public String getUsername() {
        return userKey;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
