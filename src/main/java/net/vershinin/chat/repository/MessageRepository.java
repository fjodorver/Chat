package net.vershinin.chat.repository;

import net.vershinin.chat.model.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long> {

}