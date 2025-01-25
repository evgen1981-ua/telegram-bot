package com.githab.javarushcommunity.javarush_telegrambot.command;

import com.githab.javarushcommunity.javarush_telegrambot.service.SendBotMessageService;
import com.githab.javarushcommunity.javarush_telegrambot.service.TelegramUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StatCommand implements Command{
private final SendBotMessageService sendBotMessageService;
private final TelegramUserService telegramUserService;

public final static String STAT_MESSAGE="JavaRush Telegram Bot використовує %s людей.";
@Autowired
public StatCommand(SendBotMessageService sendBotMessageService,TelegramUserService telegramUserService){
    this.sendBotMessageService=sendBotMessageService;
    this.telegramUserService=telegramUserService;
}
    @Override
    public void execute(Update update) {
int activeUserCount=telegramUserService.retrieveAllActiveUser().size();
sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(),String.format(STAT_MESSAGE,activeUserCount));
    }
}
