package com.example.kakao.auth;

import com.example.kakao.user.User;
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
        User user = kakaoService.loginWithCode(code);  // 🔥 DB 분기까지 끝난 User
        return ResponseEntity.ok(user);                // 일단은 그대로 JSON으로 확인
    }
}
