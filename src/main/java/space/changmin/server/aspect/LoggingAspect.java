package space.changmin.server.aspect;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import space.changmin.server.log.LogMessage;

/**
 * packageName    : space.changmin.server.aspect
 * fileName       : LoggingAspect
 * author         : CMLEE
 * date           : 2024-08-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-08-17        CMLEE       최초 생성
 */
@Aspect
@Slf4j
@Component
@RequiredArgsConstructor
@EnableAspectJAutoProxy
public class LoggingAspect {

    @Value("${spring.rabbitmq.exchange.log}")
    private String exchange;

    @Value("${spring.rabbitmq.routing-key.log}")
    private  String routingKey;

    private final RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
       rabbitTemplate.setExchange(exchange);
       rabbitTemplate.setRoutingKey(routingKey);
    }

    @Around("execution(* space.changmin.server.*.*(..))")
    public void logAround() {
        System.out.println("logAround");
    }

    @AfterThrowing (pointcut = "execution(* space.changmin.server.*.*(..))", throwing = "e")
    public void logAfterThrowing(Exception e) {
        log.error("Exception: {}", e.getMessage());

        LogMessage logMessage = LogMessage.builder().level(LogMessage.Level.ERROR).message(e.getMessage()).build();

        rabbitTemplate.convertAndSend(exchange, routingKey,logMessage);

    }
}
