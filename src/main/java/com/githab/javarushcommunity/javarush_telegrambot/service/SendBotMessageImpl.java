package com.githab.javarushcommunity.javarush_telegrambot.service;

import com.githab.javarushcommunity.javarush_telegrambot.bot.JavaRushTelegramBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

import static org.springframework.util.CollectionUtils.isEmpty;


@Service
public class SendBotMessageImpl implements SendBotMessageService{
private final JavaRushTelegramBot javaRushBot;
@Autowired
    public SendBotMessageImpl(JavaRushTelegramBot javaRushBot) {
        this.javaRushBot = javaRushBot;
    }

    @Override
    public void sendMessage(String chatId, String message) {
        SendMessage sendMessage=new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        sendMessage.enableHtml(true);

        try {
            javaRushBot.execute(sendMessage);

        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    @Override
    public void sendMessage(String chaId, List<String> messages) {
        if(isEmpty(messages))
            return;
        messages.forEach(m->sendMessage(String.valueOf(chaId),m));
    }
}
