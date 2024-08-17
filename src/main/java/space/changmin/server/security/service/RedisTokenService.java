package space.changmin.server.security.service;



import lombok.RequiredArgsConstructor;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import space.changmin.server.exception.InvalidTokenException;
import space.changmin.server.security.JwtUtil;

import java.util.concurrent.TimeUnit;

/**
 * @see TokenService
 */
@Service
@RequiredArgsConstructor
public class RedisTokenService implements TokenService{
    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * Redis에 RT를 저장하는 메서드입니다.
     * Key : Value 형태로 저장하여, Key는 token값을, Value에는 UserKey를 저장합니다.
     * RT 유효 기간이 지났는지 Redis에서 확인할 수 있도록 TTL을 RT 유효 시간으로 지정하였습니다 .
     *
     * @param token redis에 key로 저장될 RT를 전달 받습니다.
     * @param userId redis에 value로 저장될 userKey를 전달 받습니다.
     */
    @Override
    public void setRefreshToken(String token, String userId) {
        try {
            redisTemplate.opsForValue().set(token, userId, JwtUtil.REFRESH_TOKEN_EXPIRE_TIME, TimeUnit.MILLISECONDS);
        } catch (RuntimeException e){
            // TODO : Redis 예외 처리
        }
    }

    /**
     * RT가 Redis에 있는지 확인하는 메서드입니다.
     * 있다면 AT 재발급을 위한 userKey를 전달하고, 없다면 예외를 던집니다.
     *
     * @param key redis에 저장된 value를 얻기 위해 조회할 key를 전달 받습니다.
     * @return 조회가 되었다면 userKey를 String(문자열)로 반환합니다.
     * @throws InvalidTokenException
     *         만약 RT 유효 시간이 만료되어 redis에 값이 없다면 InvalidToken 예외를 던집니다.
     */
    @Override
    public String getTokenValue(String key) {
        String redisValue = (String) redisTemplate.opsForValue().get(key);

        // 아직 TTL이 지나지 않아 값이 있다면
        if(StringUtils.hasText(redisValue)){
            return redisValue;
        // TTL이 지나서 값이 없다면 401 만료 에러 리턴
        } else {
            throw new InvalidTokenException();
        }
    }

    /**
     * Redis에 저장된 RT를 삭제하는 메서드입니다.
     * 전달받은 token으로 redis에 저장된 값을 제거합니다.
     *
     * @param key redis에 저장된 RT를 제거하기 위한 Key(Token)을 전달 받습니다.
     */
    @Override
    public void deleteToken(String key) {
        redisTemplate.delete(key);
    }
}
