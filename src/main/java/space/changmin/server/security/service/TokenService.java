package space.changmin.server.security.service;

public interface TokenService {

    public void setRefreshToken(String token, String userId);

    public String getTokenValue(String key);

    public void deleteToken(String key);
}
