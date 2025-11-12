package com.example.kakao.auth;

import com.example.kakao.dto.KakaoUserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AuthController {

    private final KakaoService kakaoService;

    @GetMapping("/auth/login/kakao")
    public ResponseEntity<?> kakaoCallback(@RequestParam("code") String code) {
        KakaoUserResponse user = kakaoService.loginWithCode(code);
        // 맛보기 단계: 유저 정보 그대로 반환
        return ResponseEntity.ok(user);
    }
}