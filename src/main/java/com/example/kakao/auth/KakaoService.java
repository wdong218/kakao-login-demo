package com.example.kakao.auth;

import com.example.kakao.dto.KakaoTokenResponse;
import com.example.kakao.dto.KakaoUserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoService {

    private final KakaoClient kakaoClient;

    public KakaoUserResponse loginWithCode(String code) {
        KakaoTokenResponse token = kakaoClient.exchangeCodeForToken(code);
        KakaoUserResponse user = kakaoClient.getUserMe(token.getAccessToken());

        // TODO: 여기서 DB 조회 후 신규면 회원가입, 기가입이면 로그인 처리
        // TODO: 우리 서비스용 JWT 발급 지점
        log.info("Kakao login flow done. kakaoId={}", user.getId());
        return user;
    }
}