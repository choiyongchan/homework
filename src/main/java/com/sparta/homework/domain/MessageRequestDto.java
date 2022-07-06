package com.sparta.homework.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class MessageRequestDto {
    private final String username;
    private final String title;
    private final String password;
    private final String content;


}
