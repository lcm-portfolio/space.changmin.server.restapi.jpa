package space.changmin.server.address;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * packageName    : space.changmin.server.address
 * fileName       : AddressRepository
 * author         : CMLEE
 * date           : 2024-08-18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-08-18        CMLEE       최초 생성
 */
public interface AddressRepository extends JpaRepository<Address, UUID> {
    Page<Address> findAllByUserId(String userId, Pageable pageable);
}
