package space.changmin.server.log;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

/**
 * packageName    : space.changmin.server.log
 * fileName       : LogMessage
 * author         : CMLEE
 * date           : 2024-08-18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-08-18        CMLEE       최초 생성
 */

@Entity
@Table(name = "log_message")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogMessage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private String message;

    private Level level;

    private String location;


    public enum Level{
        INFO, WARN, ERROR
    };


}
