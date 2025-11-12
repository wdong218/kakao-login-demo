package com.example.kakao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .formLogin(form -> form.disable())      // ✅ 기본 로그인 폼 끔
                .httpBasic(basic -> basic.disable())    // ✅ Basic Auth 끔
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/auth/login/kakao", "/auth/login/kakao/**",
                                "/health", "/", "/error"
                        ).permitAll()
                        .anyRequest().permitAll()           // 개발 중엔 전부 오픈 (JWT 붙일 땐 authenticated로 변경)
                );
        return http.build();
    }
}