package com.example.kakao.user;

import com.example.kakao.dto.KakaoUserResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User loginOrRegister(KakaoUserResponse kakaoUser) {

        Long kakaoId = kakaoUser.getId(); // 네 DTO 구조에 맞게 수정

        // 1) 이미 있으면 그대로 리턴 = 로그인
        return userRepository.findByKakaoId(kakaoId)
                .orElseGet(() -> {
                    // 2) 없으면 새로 생성 = 회원가입
                    User newUser = User.builder()
                            .kakaoId(kakaoId)
                            .email(kakaoUser.getKakaoAccount().getEmail())
                            .nickname(kakaoUser.getProperties().getNickname())
                            .profileImageUrl(kakaoUser.getProperties().getProfileImage())
                            .createdAt(LocalDateTime.now())
                            .updatedAt(LocalDateTime.now())
                            .build();

                    return userRepository.save(newUser);
                });
    }
}
