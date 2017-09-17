package net.vershinin.chat.controller;

import net.vershinin.chat.model.Message;
import net.vershinin.chat.model.User;
import net.vershinin.chat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping("/api/v1/messages/")
    public Set<Message> findAll(){
        return messageService.findAll();
    }

    @MessageMapping("/send")
    @SendTo("/messages/receive")
    public Message sendMessage(Message message, @AuthenticationPrincipal User user){
        message.setUser(user);
        messageService.save(message);
        return message;
    }
}