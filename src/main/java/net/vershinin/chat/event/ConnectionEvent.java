package net.vershinin.chat.event;

import lombok.Data;
import org.springframework.messaging.simp.stomp.StompCommand;

import java.io.Serializable;
import java.security.Principal;

@Data
public class ConnectionEvent implements Serializable {

    private final Principal principal;

    private final StompCommand command;

    public ConnectionEvent(StompCommand command, Principal principal) {
        this.command = command;
        this.principal = principal;
    }
}