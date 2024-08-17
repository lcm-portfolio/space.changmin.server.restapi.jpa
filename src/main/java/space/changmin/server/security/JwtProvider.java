package com.example.demo.security;

import com.example.demo.exceptions.InvalidTokenException;
import com.example.demo.security.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.example.demo.security.userdetails.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

@Slf4j
@Component
public class JwtProvider {
    private final TokenService tokenService;

    private final Key SECRET_KEY;

    public JwtProvider(@Value("${jwt.secret.key}") String key, TokenService tokenService) {
        this.tokenService = tokenService;
        this.SECRET_KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));
    }

    /**
     * 처음 토큰 발급 시 사용되는 메서드입니다.
     *
     * @param userId 토큰 발급 시 subject에 사용되는 userKey
     * @return 발급된 토큰을 String(문자열)로 반환합니다.
     * */
    public String generateAccessToken(String userId){
        Date now = new Date();
        Date accessTokenExpiresIn = new Date(now.getTime() + JwtUtil.ACCESS_TOKEN_EXPIRE_TIME);

        return Jwts.builder()
                .setSubject(userId)
                .claim(JwtUtil.AUTHORIZATION_HEADER, Authority.ROLE_USER)
                .setIssuedAt(now)
                .setExpiration(accessTokenExpiresIn)
                .signWith(SECRET_KEY, SignatureAlgorithm.HS512)
                .compact();
    }

    /**
     * RT를 발급하고, Redis에 저장하는 메서드입니다.
     *
     * @param userKey RT 발급 시 Subject에 사용되는 userKey
     *
     * @return 발급된 Refresh 토큰을 String(문자열)로 반환합니다.
     */
    public String generateRefreshToken(String userKey){
        Date now = new Date();
        Date refreshTokenExpiresIn = new Date(now.getTime() + JwtUtil.REFRESH_TOKEN_EXPIRE_TIME);

        String refreshToken = Jwts.builder()
                .setSubject(userKey)
                .claim(JwtUtil.AUTHORIZATION_HEADER, Authority.ROLE_USER)
                .setIssuedAt(now)
                .setExpiration(refreshTokenExpiresIn)
                .signWith(SECRET_KEY, SignatureAlgorithm.HS512)
                .compact();

        tokenService.setRefreshToken(refreshToken, userKey);

        return refreshToken;
    }

    /**
     * 토큰에서 UserKey, 권한을 담은 객체를 반환하는 메서드입니다.
     *
     * @param token 권한을 얻을 토큰을 전달 받습니다.
     * @return 정보를 담은 UserDetails 객체를 리턴합니다.
     * @throws com.example.demo.exceptions.InvalidTokenException
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

}
