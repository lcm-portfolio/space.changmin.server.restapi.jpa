package space.changmin.server.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import space.changmin.server.security.service.UserDetailsServiceImpl;

/**
 * packageName    : space.changmin.server.controllers
 * fileName       : UserController
 * author         : CMLEE
 * date           : 2024-08-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-08-17        CMLEE       최초 생성
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserDetailsServiceImpl userService;

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUp(@RequestBody UserDTO userDTO) {
        userService.signUp(userDTO);
    }






}
