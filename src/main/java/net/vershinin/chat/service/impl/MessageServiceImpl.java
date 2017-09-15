package net.vershinin.chat.service.impl;

import com.google.common.collect.Sets;
import net.vershinin.chat.model.Message;
import net.vershinin.chat.repository.MessageRepository;
import net.vershinin.chat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Set<Message> findAll() {
        return Sets.newHashSet(messageRepository.findAll());
    }

    @Override
    public void save(Message message) {
        messageRepository.save(message);
    }
}
