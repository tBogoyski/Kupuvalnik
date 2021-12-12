package com.project.kupuvalnik.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "messages")
@Entity
public class MessageEntity extends BaseEntity {

    private String message;
    private UserEntity sender;
    private UserEntity reciever;

    public MessageEntity() {
    }

    @Column(nullable = false, columnDefinition = "TEXT")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @ManyToOne
    public UserEntity getSender() {
        return sender;
    }

    public void setSender(UserEntity sender) {
        this.sender = sender;
    }

    @ManyToOne
    public UserEntity getReciever() {
        return reciever;
    }

    public void setReciever(UserEntity reciever) {
        this.reciever = reciever;
    }
}
