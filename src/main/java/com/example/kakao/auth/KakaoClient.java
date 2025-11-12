package com.example.kakao.auth;

import com.example.kakao.dto.KakaoTokenResponse;
import com.example.kakao.dto.KakaoUserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoClient {

    private final KakaoAuthProperties props;
    private final WebClient webClient = WebClient.builder().build();

    public KakaoTokenResponse exchangeCodeForToken(String code) {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("grant_type", "authorization_code");
        form.add("client_id", props.getAuth().getClient());
        form.add("redirect_uri", props.getAuth().getRedirect());
        form.add("code", code);

        return webClient.post()
                .uri(props.getApi().getTokenUri())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue(form)
                .retrieve()
                .bodyToMono(KakaoTokenResponse.class)
                .doOnNext(t -> log.info("Kakao token: {}", t))
                .onErrorResume(e -> {
                    log.error("Token exchange failed", e);
                    return Mono.error(e);
                })
                .block();
    }

    public KakaoUserResponse getUserMe(String accessToken) {
        return webClient.get()
                .uri(props.getApi().getUserinfoUri())
                .headers(h -> h.setBearerAuth(accessToken))
                .retrieve()
                .bodyToMono(KakaoUserResponse.class)
                .doOnNext(u -> log.info("Kakao user: {}", u))
                .onErrorResume(e -> {
                    log.error("Get user failed", e);
                    return Mono.error(e);
                })
                .block();
    }
}