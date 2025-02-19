package com.githab.javarushcommunity.javarush_telegrambot.service;

import java.util.List;

public interface SendBotMessageService {
    void sendMessage(String chatId,String message);
void sendMessage(String chaId, List<String> messages);

}
