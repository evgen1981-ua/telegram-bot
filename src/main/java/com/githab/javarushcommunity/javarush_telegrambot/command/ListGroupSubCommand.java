package com.githab.javarushcommunity.javarush_telegrambot.command;

import com.githab.javarushcommunity.javarush_telegrambot.repository.entity.TelegramUser;
import com.githab.javarushcommunity.javarush_telegrambot.service.SendBotMessageService;
import com.githab.javarushcommunity.javarush_telegrambot.service.TelegramUserService;
import jakarta.ws.rs.NotFoundException;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.stream.Collectors;

public class ListGroupSubCommand implements Command{

    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;

    public ListGroupSubCommand(SendBotMessageService sendBotMessageService,TelegramUserService telegramUserService){
        this.sendBotMessageService=sendBotMessageService;
        this.telegramUserService=telegramUserService;
    }

    @Override
    public void execute(Update update) {
        TelegramUser telegramUser=telegramUserService.findByChatId(String.valueOf(update.getMessage()
                .getChatId())).orElseThrow(NotFoundException::new);
        String message="Я знайшов усі підписки на групи: \n\n";
        String collectedGroup=telegramUser.getGroupSubs().stream().map(it->"група:"+it.getTitle()+" ,ID"+it.getId()+"\n")
                .collect(Collectors.joining());
sendBotMessageService.sendMessage(telegramUser.getChat_id(),message+collectedGroup);
    }
}
