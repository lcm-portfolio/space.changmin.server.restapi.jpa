package space.changmin.server.user;

import org.springframework.data.jpa.repository.JpaRepository;
import space.changmin.server.user.User;

import java.util.Optional;
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
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsername(String username);


}
