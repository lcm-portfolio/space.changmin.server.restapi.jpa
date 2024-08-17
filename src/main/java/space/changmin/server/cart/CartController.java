package space.changmin.server.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import space.changmin.server.annotations.CurrentUserKey;


/**
 * packageName    : space.changmin.server.controllers
 * fileName       : CartController
 * author         : CMLEE
 * date           : 2024-08-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-08-17        CMLEE       최초 생성
 */


@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {


    private final CartService cartService;

    @GetMapping
    public ResponseEntity<?> getMyCart(
            @CurrentUserKey String userId,
            @RequestParam Integer page,
            @RequestParam Integer size
    ){

        Pageable pageable = PageRequest.of(page, size);

        Page<Cart> carts = cartService.findAllByUserId(userId, pageable);

        return ResponseEntity.ok(carts);

    }





}
