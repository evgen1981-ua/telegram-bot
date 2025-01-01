package com.githab.javarushcommunity.javarush_telegrambot.command;

import com.githab.javarushcommunity.javarush_telegrambot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.githab.javarushcommunity.javarush_telegrambot.command.CommandName.*;

public class HelpCommand implements Command{
private final SendBotMessageService sendBotMessageService;
public static final String HELP_MESSAGE=String.format("Доступні команди\n"
        +"%s - почати роботу\n"
        +"%s - приостановити роботу\n"
        +"%s - отримати допомогу\n",START.getCommandName(),STOP.getCommandName(),HELP.getCommandName());

    public HelpCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(),HELP_MESSAGE);
    }
}