package com.githab.javarushcommunity.javarush_telegrambot.repository.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "tg_user")
public class TelegramUser {
   @Id
    @Column(name = "chat_id")
    private String chat_id;

   @Column(name = "active")
    private boolean active;

   @ManyToMany(mappedBy = "users",fetch = FetchType.EAGER)
    private List<GroupSub> groupSubs;
}
