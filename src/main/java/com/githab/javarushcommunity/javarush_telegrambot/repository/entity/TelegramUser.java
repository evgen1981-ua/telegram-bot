package com.githab.javarushcommunity.javarush_telegrambot.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tg_user")
public class TelegramUser {
   @Id
    @Column(name = "chat_id")
    private String chat_id;

   @Column(name = "active")
    private boolean active;

}
