package net.vershinin.chat.service;

import net.vershinin.chat.model.Message;

import java.util.Set;

public interface MessageService {

    Set<Message> findAll();

    void save(Message message);
}