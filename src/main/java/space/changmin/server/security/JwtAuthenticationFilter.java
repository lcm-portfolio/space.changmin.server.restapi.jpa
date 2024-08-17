package space.changmin.server.security;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwt = resolveToken(request);
        jwt = (jwt == null || (jwt.equals("null"))) ? null : jwt;

        try {
            if(StringUtils.hasText(jwt) && jwtProvider.validateToken(jwt)){
                Authentication authentication = jwtProvider.getAuthentication(jwt);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.error("서명 혹은 구조가 잘못된 JWT");
            sendErrorResponse(response, 400, "서명 혹은 구조가 잘못된 토큰입니다.");
        } catch (ExpiredJwtException e){
            log.error("유효기간이 만료된 JWT");
            sendErrorResponse(response, 401, "유효 기간이 만료된 토큰입니다.");
        } catch (UnsupportedJwtException e){
            log.error("지원하는 형식과 일치하지 않는 JWT");
            sendErrorResponse(response, 400, "지원하는 형식과 다른 토큰입니다.");
        } catch (IllegalArgumentException e){
            log.error("Claims가 비어있는 토큰");
            sendErrorResponse(response, 400, "지원하는 형식과 다른 토큰입니다.");
        } catch (RuntimeException e){
            e.printStackTrace();
            log.error("토큰 발급 중 에러");
            sendErrorResponse(response, 400, "토큰 발급 중 에러가 발생했습니다.");
        }

        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(JwtUtil.AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(JwtUtil.BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private void sendErrorResponse(HttpServletResponse response, int sc, String msg) throws IOException {
        response.sendError(sc, msg);
    }
}
