package com.example.kakao.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter @Setter
@Configuration
@ConfigurationProperties(prefix = "kakao")
public class KakaoAuthProperties {
    // kakao.auth.client, kakao.auth.redirect
    private Auth auth = new Auth();
    // kakao.api.token-uri, kakao.api.userinfo-uri
    private Api api = new Api();

    @Getter @Setter
    public static class Auth {
        private String client;      // REST API Key
        private String redirect;    // redirect_uri
    }
    @Getter @Setter
    public static class Api {
        private String tokenUri;
        private String userinfoUri;
    }
}