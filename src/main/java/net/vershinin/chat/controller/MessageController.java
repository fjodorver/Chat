package net.vershinin.chat.controller;

import net.vershinin.chat.model.Message;
import net.vershinin.chat.service.MessageService;
import net.vershinin.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Set;

@RestController
public class MessageController {

    private final MessageService messageService;

    private final UserService userService;

    @Autowired
    public MessageController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @RequestMapping("/messages/")
    public Set<Message> findAll(){
        return messageService.findAll();
    }

    @MessageMapping("/send")
    @SendTo("/topic/receive")
    public Message sendMessage(Principal principal, Message message){
        messageService.save(message);
        return message;
    }
}