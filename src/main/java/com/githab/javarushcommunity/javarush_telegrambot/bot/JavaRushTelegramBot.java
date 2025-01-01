package com.githab.javarushcommunity.javarush_telegrambot.bot;


import com.githab.javarushcommunity.javarush_telegrambot.command.CommandContainer;
import com.githab.javarushcommunity.javarush_telegrambot.service.SendBotMessageImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static com.githab.javarushcommunity.javarush_telegrambot.command.CommandName.NO;


@Component

public class JavaRushTelegramBot extends TelegramLongPollingBot {

    private final CommandContainer commandContainer;
public final String COMMAND_PREFIX="/";
    public JavaRushTelegramBot(){
        this.commandContainer=new CommandContainer(new SendBotMessageImpl(this));
    }
    @Override
    public void onUpdateReceived(Update update){
if(update.hasMessage() && update.getMessage().hasText()){
    String message=update.getMessage().getText().trim();
    if (message.startsWith(COMMAND_PREFIX)) {
        String commandIdentifier = message.split(" ")[0].toLowerCase();
        commandContainer.retrieveCommand(commandIdentifier).execute(update);
    }else {
        commandContainer.retrieveCommand(NO.getCommandName()).execute(update);
    }


}
    }

    public String getBotUsername(){
        return "@test_javarush1_community_bot";
    }
@Override
    public String getBotToken(){
        return "7168425753:AAEluuiQNbM_UAqhvItXQdIB68rN2QlaO9U";
    }
}
