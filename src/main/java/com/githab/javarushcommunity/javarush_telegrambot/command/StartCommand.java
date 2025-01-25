package com.githab.javarushcommunity.javarush_telegrambot.command;

import com.githab.javarushcommunity.javarush_telegrambot.repository.entity.TelegramUser;
import com.githab.javarushcommunity.javarush_telegrambot.service.SendBotMessageService;
import com.githab.javarushcommunity.javarush_telegrambot.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StartCommand implements Command{
    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;
    public static final String START_MESSAGE="Прииіт! Я javarush telegram bot.";

    public StartCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService=telegramUserService;
    }

    public void execute(Update update){
       String chatId= update.getMessage().getChatId().toString();
       telegramUserService.findByChatId(chatId).ifPresentOrElse(user->{user.setActive(true);
       telegramUserService.save(user);},()->{
           TelegramUser telegramUser=new TelegramUser();
           telegramUser.setActive(true);
           telegramUser.setChat_id(chatId);
           telegramUserService.save(telegramUser);
       });
        sendBotMessageService.sendMessage(chatId,START_MESSAGE);
    }

}
