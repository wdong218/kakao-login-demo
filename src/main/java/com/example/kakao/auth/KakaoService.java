package com.example.kakao.auth;

import com.example.kakao.dto.KakaoTokenResponse;
import com.example.kakao.dto.KakaoUserResponse;
import com.example.kakao.user.User;
import com.example.kakao.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoService {

    private final KakaoClient kakaoClient;
    private final UserService userService;

    public User loginWithCode(String code) {
        // 1. 인가 코드로 토큰 발급
        KakaoTokenResponse token = kakaoClient.exchangeCodeForToken(code);

        // 2. 토큰으로 카카오 사용자 정보 조회
        KakaoUserResponse kakaoUser = kakaoClient.getUserMe(token.getAccessToken());

        // 3. DB에서 유저 찾거나 없으면 새로 생성
        User user = userService.loginOrRegister(kakaoUser);

        log.info("Kakao login flow done. kakaoId={}, userId={}", kakaoUser.getId(), user.getId());
        return user;
    }
}
