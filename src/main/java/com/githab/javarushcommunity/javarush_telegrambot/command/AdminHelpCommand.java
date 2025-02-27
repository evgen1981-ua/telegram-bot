package com.githab.javarushcommunity.javarush_telegrambot.command;

import com.githab.javarushcommunity.javarush_telegrambot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.githab.javarushcommunity.javarush_telegrambot.command.CommandName.STAT;

public class AdminHelpCommand implements Command {

    public static final String ADMIN_HELP_MESSAGE=String.format("<b> Доступные команды администратора</b>"
    +"<b>Получить статистику</b>\n"
    +"%s-статистика бота\n",STAT.getCommandName());

    private final SendBotMessageService sendBotMessageService;

    public AdminHelpCommand (SendBotMessageService sendBotMessageService){
        this.sendBotMessageService=sendBotMessageService;
    }
    @Override
    public void execute(Update update){
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(),ADMIN_HELP_MESSAGE);
    }

}
