package net.vershinin.chat.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@Entity
@EqualsAndHashCode(of = "id")
public class Message implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String content;

    @ManyToOne
    private User user;
}