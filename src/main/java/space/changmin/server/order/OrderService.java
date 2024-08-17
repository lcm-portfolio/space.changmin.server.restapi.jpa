package space.changmin.server.order;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

/**
 * packageName    : space.changmin.server.order
 * fileName       : OrderService
 * author         : CMLEE
 * date           : 2024-08-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-08-17        CMLEE       최초 생성
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService implements CommandLineRunner {

        private final OrderRepository orderRepository;

        @Transactional
        public void createOrder(OrderDTO orderDTO) {



            Order order = orderDTO.toEntity();
            orderRepository.save(order);

        }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("OrderService.run");
        log.error("OrderService.run");


    }
}
