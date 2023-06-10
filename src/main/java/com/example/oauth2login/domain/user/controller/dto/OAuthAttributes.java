package com.example.oauth2login.domain.user.controller.dto;

import com.example.oauth2login.domain.user.entity.Role;
import com.example.oauth2login.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Getter
@Builder
@RequiredArgsConstructor
public class OAuthAttributes {

    private final Map<String, Object> attributes;

    private final String nameAttributeKey;

    private final String nickname;

    private final String mobile;

    private final String birthDay;

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        if("naver".equals(registrationId)) {
            return ofNaver("id", attributes);
        }
        else {
            return null;
        }
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes){

        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .nickname((String) response.get("nickname"))
                .mobile((String) response.get("mobile"))
                .birthDay((String) response.get("birthDay"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();

    }

    public User toEntity(){
        return User.builder()
                .nickname(nickname)
                .mobile(mobile)
                .role(Role.GUEST)
                .build();
    }
}
