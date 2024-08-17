package space.changmin.server.log;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * packageName    : space.changmin.server.log
 * fileName       : LogEventHandler
 * author         : CMLEE
 * date           : 2024-08-18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-08-18        CMLEE       최초 생성
 */

@Component
@RequiredArgsConstructor
@Slf4j
public class LogEventHandler {


    private final LogService logService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${spring.rabbitmq.queue.log}", durable = "true"),
            exchange = @Exchange(value = "${spring.rabbitmq.exchange.log}", type = "${spring.rabbitmq.exchange.type.log}"),
            key = "${spring.rabbitmq.queue.log}"))
    public void handleLog(final LogMessage message){

        log.error("LogEventHandler.handleLog: {}", message);

        logService.saveLog(message);

    }

}
