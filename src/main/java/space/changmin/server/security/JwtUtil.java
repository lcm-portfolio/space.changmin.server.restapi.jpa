package space.changmin.server.security;

public class JwtUtil {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";
    public static final long ACCESS_TOKEN_EXPIRE_TIME  = 1000 * 60 * 60;
    public static final long REFRESH_TOKEN_EXPIRE_TIME  = 1000 * 60 * 60 * 24 * 7;
}
