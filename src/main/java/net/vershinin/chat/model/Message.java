package net.vershinin.chat.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@Entity
public class Message implements Serializable {

    @Id
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}