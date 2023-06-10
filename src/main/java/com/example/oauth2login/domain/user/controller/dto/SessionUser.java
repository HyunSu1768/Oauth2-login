package com.example.oauth2login.domain.user.controller.dto;

import com.example.oauth2login.domain.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class SessionUser implements Serializable {

    private String nickname;

    private String mobile;

    public SessionUser(User user){
        this.nickname = user.getNickname();
        this.mobile = user.getMobile();
    }
}
