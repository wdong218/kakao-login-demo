package com.example.kakao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter @ToString
public class KakaoUserResponse {
    private Long id;
    private Map<String, Object> properties;
    @JsonProperty("kakao_account")
    private Map<String, Object> kakaoAccount;
}