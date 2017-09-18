package net.vershinin.chat.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Connection implements Serializable {

    private final Status status;

    private final User user;

    public Connection(Status status, User user) {
        this.status = status;
        this.user = user;
    }

    public enum Status {
        CONNECTED, DISCONNECTED;
    }
}
