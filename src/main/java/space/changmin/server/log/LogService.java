package space.changmin.server.log;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * packageName    : space.changmin.server.log
 * fileName       : LogService
 * author         : CMLEE
 * date           : 2024-08-18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-08-18        CMLEE       최초 생성
 */

@Service
@RequiredArgsConstructor
public class LogService {

    private final LogRepository logRepository;


    public void saveLog(LogMessage message){
        logRepository.save(message);
    }



}
