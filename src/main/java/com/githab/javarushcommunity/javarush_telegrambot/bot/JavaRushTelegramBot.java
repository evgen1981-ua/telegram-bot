package com.githab.javarushcommunity.javarush_telegrambot.bot;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Component

public class JavaRushTelegramBot extends TelegramLongPollingBot {

    private String username;

    private String token;
    @Override
    public void onUpdateReceived(Update update){
if(update.hasMessage() && update.getMessage().hasText()){
    String message=update.getMessage().getText().trim();
    String chatId=update.getMessage().getChatId().toString();

    SendMessage sm=new SendMessage();
    sm.setChatId(chatId);
    sm.setText(message);
    try {
        execute(sm);
    }catch (TelegramApiException e){
        e.printStackTrace();
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
