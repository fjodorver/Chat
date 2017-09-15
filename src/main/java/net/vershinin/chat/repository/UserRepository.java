package net.vershinin.chat.repository;

import net.vershinin.chat.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}