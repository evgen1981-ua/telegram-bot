package com.githab.javarushcommunity.javarush_telegrambot.command;

import com.githab.javarushcommunity.javarush_telegrambot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class UnknownCommand implements Command{
    private final SendBotMessageService sendBotMessageService;
public static final String UNKNOWN_MESSAGE="Не розумію вас введіть \\help";
    public UnknownCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(),UNKNOWN_MESSAGE);
    }
}
