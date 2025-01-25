package com.githab.javarushcommunity.javarush_telegrambot.command;

import com.githab.javarushcommunity.javarush_telegrambot.service.SendBotMessageService;
import com.githab.javarushcommunity.javarush_telegrambot.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StopCommand implements Command{
    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;
public static final String END_MESSAGE="Деактивував усі ваші підписки.";
    public StopCommand(SendBotMessageService sendBotMessageService,TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService=telegramUserService;
    }


    @Override
    public void execute(Update update) {
            sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(),END_MESSAGE);
            telegramUserService.findByChatId(update.getMessage().getChatId().toString()).ifPresent(it->
        {it.setActive(false);
        telegramUserService.save(it);});

    }
}
