package space.changmin.server.authentication;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

/**
 * packageName    : space.changmin.server.repositories
 * fileName       : AccountRepository
 * author         : CMLEE
 * date           : 2024-08-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-08-17        CMLEE       최초 생성
 */
public interface UserRepository extends JpaRepository<User, UUID> {


}
