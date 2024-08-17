package space.changmin.server.config;


import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import space.changmin.server.security.Authority;
import space.changmin.server.security.JwtAuthenticationFilter;
import space.changmin.server.security.JwtProvider;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {


    @Value("#{'${spring.security.origin}'.split(',')}")
    private List<String> allowedOriginPaths;
    @Value("${spring.security.cors.allow.methods:1,2,3,4,5,6}")
    private String[] allowedMethods;

    private final JwtProvider jwtProvider;

    private final String[] EXCLUDED_END_POINTS = {
            "/signin"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)  throws Exception {

        try {
            http
                    .csrf(AbstractHttpConfigurer::disable)
                    .httpBasic(AbstractHttpConfigurer::disable)
                    .formLogin(AbstractHttpConfigurer::disable)
                    .headers(headersConfig -> headersConfig
                            .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                    .cors(cors -> cors.configurationSource(getConfigurationSource()))
                    .authorizeHttpRequests((authorizeRequest) -> {
                        authorizeRequest
                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                .requestMatchers(EXCLUDED_END_POINTS).permitAll()
                                .requestMatchers("/**").hasRole(Authority.ROLE_USER.getAuthority())
                                .anyRequest().authenticated();
                    })
                    .exceptionHandling(handling ->
                            handling
                                    .authenticationEntryPoint(authenticationEntryPoint)
                                     .accessDeniedHandler(accessDeniedHandler))
                    .addFilterBefore(new JwtAuthenticationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);

            return http.build();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }


    }

    private CorsConfigurationSource getConfigurationSource() {
        return request -> {
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowedOrigins(null);
            config.setAllowedMethods(null);
            config.setAllowedHeaders(null);
            config.setAllowCredentials(true);
            config.setMaxAge(3600L);
            return config;
        };
    }

    private final AccessDeniedHandler accessDeniedHandler = ((request, response, accessDeniedException) -> {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    });

    private final AuthenticationEntryPoint authenticationEntryPoint = ((request, response, authException) -> {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    });





}
