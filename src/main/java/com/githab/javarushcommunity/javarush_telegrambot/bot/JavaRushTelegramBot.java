package com.githab.javarushcommunity.javarush_telegrambot.bot;


import com.githab.javarushcommunity.javarush_telegrambot.command.CommandContainer;
import com.githab.javarushcommunity.javarush_telegrambot.javarushclient.JavaRushGroupClient;
import com.githab.javarushcommunity.javarush_telegrambot.javarushclient.JavaRushGroupClientIml;
import com.githab.javarushcommunity.javarush_telegrambot.service.GroupSubService;
import com.githab.javarushcommunity.javarush_telegrambot.service.GroupSubServiceIml;
import com.githab.javarushcommunity.javarush_telegrambot.service.SendBotMessageImpl;
import com.githab.javarushcommunity.javarush_telegrambot.service.TelegramUserServiceIml;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

import static com.githab.javarushcommunity.javarush_telegrambot.command.CommandName.NO;


@Component

public class JavaRushTelegramBot extends TelegramLongPollingBot {
String username="@test_javarush1_community_bot";
String token="7168425753:AAHE7zJIGFlbwoHUHXUOs7AFqEwYwGTJfzU";


    private final CommandContainer commandContainer;

public final String COMMAND_PREFIX="/";

    public JavaRushTelegramBot(TelegramUserServiceIml telegramUserServiceIml, GroupSubService groupSubServiceIml,
                               JavaRushGroupClientIml javaRushGroupClient, @Value("#{'${bot.admins}'.split('.')}") List<String> admins){
        this.commandContainer=new CommandContainer(new SendBotMessageImpl(this),telegramUserServiceIml,
                javaRushGroupClient,groupSubServiceIml,admins);

    }



    @Override
    public void onUpdateReceived(Update update){
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();

            if (message.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = message.split(" ")[0].toLowerCase();
                commandContainer.retrieveCommand(commandIdentifier,update.getMessage().getFrom().getUserName()).execute(update);
            } else {
                commandContainer.retrieveCommand(NO.getCommandName(),update.getMessage().getFrom().getUserName()).execute(update);
            }
        }
    }

    public String getBotUsername(){
        return username;
    }
@Override
    public String getBotToken(){
        return token;
    }
}
