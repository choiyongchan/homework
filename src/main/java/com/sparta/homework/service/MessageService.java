package com.sparta.homework.service;

import com.sparta.homework.domain.Message;
import com.sparta.homework.domain.MessageRepository;
import com.sparta.homework.domain.MessageRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    @Transactional
    public String update(Long id, MessageRequestDto requestDto){
        Message message = messageRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );

        if (! message.getPassword().equals(requestDto.getPassword()) ){
           return "비밀번호가 일치하지 않습니다.";
        } else {
            message.update(requestDto);
            return "수정하였습니다";
        }
    }
}
