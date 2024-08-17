package space.changmin.server.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import space.changmin.server.exception.InvalidTokenException;
import space.changmin.server.security.service.TokenService;
import space.changmin.server.security.userdetails.CustomUserDetails;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

@Slf4j
@Component
public class JwtProvider {
    private final  TokenService tokenService;

    private final Key SECRET_KEY;

    public JwtProvider(@Value("${jwt.secret.key}") String key, TokenService tokenService) {
        this.SECRET_KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));
        this.tokenService = tokenService;
    }


    /**
     * 토큰에서 UserKey, 권한을 담은 객체를 반환하는 메서드입니다.
     *
     * @param token 권한을 얻을 토큰을 전달 받습니다.
     * @return 정보를 담은 UserDetails 객체를 리턴합니다.
     * @throws InvalidTokenException
     *         만약 권한이 null일 경우 잘못된 토큰 예외(InvalidToken)를 던집니다.
     */
    public Authentication getAuthentication(String token){
        Claims claims = parseClaims(token);

        if (claims.get(JwtUtil.AUTHORIZATION_HEADER) == null) {
            log.error("권한 정보가 없는 토큰");
            throw new InvalidTokenException();
        }

        Collection<? extends GrantedAuthority> authorities = Arrays.stream(claims.get(JwtUtil.AUTHORIZATION_HEADER).toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .toList();

        UserDetails principal = CustomUserDetails.builder().userKey(claims.getSubject())
                .authorities(authorities).build();

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public boolean validateToken(String token) throws RuntimeException{
        Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token);
        return true;
    }

    private Claims parseClaims(String token){
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e){
            return e.getClaims();
        }

    }

    public String generateRefreshToken(String userId, String number015){
        Date now = new Date();
        Date refreshTokenExpiresIn = new Date(now.getTime() + JwtUtil.REFRESH_TOKEN_EXPIRE_TIME);

        String refreshToken = Jwts.builder()
                .setSubject(userId)
                .claim(JwtUtil.AUTHORIZATION_HEADER, Authority.ROLE_USER)
                .setIssuedAt(now)
                .setExpiration(refreshTokenExpiresIn)
                .signWith(SECRET_KEY, SignatureAlgorithm.HS512)
                .compact();

        tokenService.setRefreshToken(refreshToken, userId);

        return refreshToken;
    }

}
