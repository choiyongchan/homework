package com.sparta.homework.controller;

import com.sparta.homework.domain.Message;
import com.sparta.homework.domain.MessageRepository;
import com.sparta.homework.domain.MessageRequestDto;
import com.sparta.homework.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class MessageController {

    private final MessageRepository messageRepository;
    private final MessageService messageService;

    @GetMapping("/api/messages")
    public List<Message> getMessages() {
        return messageRepository.findAllByOrderByModifiedAtDesc();
    }

    @PostMapping("/api/messages")
    public Message createMessage(@RequestBody MessageRequestDto requestDto){
        Message message = new Message(requestDto);
        return messageRepository.save(message);
    }

    @PutMapping("/api/messages/{id}")
    public String updateMessage(@PathVariable Long id, @RequestBody MessageRequestDto requestDto){
        String result;
        result = messageService.update(id, requestDto);
        return result;
    }

    @DeleteMapping("/api/messages/{id}")
    public String deleteMessage(@PathVariable Long id, @RequestBody MessageRequestDto requestDto){

        Message message = messageRepository.findById(id).get();
        if (! message.getPassword().equals(requestDto.getPassword()) ){
            return "비밀번호가 일치하지 않습니다";
        } else {
            messageRepository.deleteById(id);
            return "삭제하였습니다";
        }

    }

}
