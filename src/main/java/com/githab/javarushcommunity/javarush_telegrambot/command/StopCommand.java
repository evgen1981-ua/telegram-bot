package com.githab.javarushcommunity.javarush_telegrambot.command;

import com.githab.javarushcommunity.javarush_telegrambot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StopCommand implements Command{
    private final SendBotMessageService sendBotMessageService;
public static final String END_MESSAGE="Деактивував усі ваші підписки.";
    public StopCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }


    @Override
    public void execute(Update update) {
        SendMessage sendMessage=new SendMessage();
            sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(),END_MESSAGE);

    }
}
