package com.githab.javarushcommunity.javarush_telegrambot.service;

import com.githab.javarushcommunity.javarush_telegrambot.repository.entity.TelegramUser;

import java.util.List;
import java.util.Optional;

public interface TelegramUserService {
    void save(TelegramUser telegramUser);
    List<TelegramUser> findAllActiveUser();
    Optional<TelegramUser> findByChatId(String chatId);
    List<TelegramUser> findAllInActiveUsers();


}
