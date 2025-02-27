package com.githab.javarushcommunity.javarush_telegrambot.command;

import com.githab.javarushcommunity.javarush_telegrambot.repository.entity.GroupSub;
import com.githab.javarushcommunity.javarush_telegrambot.repository.entity.TelegramUser;
import com.githab.javarushcommunity.javarush_telegrambot.service.GroupSubService;
import com.githab.javarushcommunity.javarush_telegrambot.service.SendBotMessageService;
import com.githab.javarushcommunity.javarush_telegrambot.service.TelegramUserService;
import jakarta.ws.rs.NotFoundException;
import org.springframework.util.CollectionUtils;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import static com.githab.javarushcommunity.javarush_telegrambot.command.CommandName.DELETE_GROUP_SUB;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.apache.commons.lang3.StringUtils.isNumeric;

public class DeleteGroupSubCommand implements Command{

    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;
    private final GroupSubService groupSubService;

    public DeleteGroupSubCommand(SendBotMessageService sendBotMessageService,
                                 TelegramUserService telegramUserService, GroupSubService groupSubService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
        this.groupSubService = groupSubService;
    }



    @Override
    public void execute(Update update) {
        if (update.getMessage().getText().equalsIgnoreCase(DELETE_GROUP_SUB.getCommandName())) {
            sendGroupIdList(String.valueOf(update.getMessage().getChatId()));
            return;
        }
        String groupId = update.getMessage().getText().split(SPACE)[1];
        String chatId = String.valueOf(update.getMessage().getChatId());

        if (isNumeric(groupId)) {
            Optional<GroupSub> optionalGroupSub = groupSubService.findById(Integer.valueOf(groupId));
            if (optionalGroupSub.isPresent()) {
                GroupSub groupSub = optionalGroupSub.get();
                TelegramUser telegramUser = telegramUserService.findByChatId(chatId).orElseThrow(NotFoundException::new);
                groupSub.getUsers().remove(telegramUser);
                groupSubService.save(groupSub);
                sendBotMessageService.sendMessage(chatId, String.format("Удалил подписку на группу: %s", groupSub.getTitle()));

            } else {
                sendBotMessageService.sendMessage(chatId, "Не нашел такой группы =/");
            }

        } else {
            sendBotMessageService.sendMessage(chatId, "неправильный формат ID группы");

        }
    }
    private void sendGroupIdList (String chatId){
        String message;
        List<GroupSub> groupSubs=telegramUserService.findByChatId(chatId).orElseThrow(NotFoundException::new)
                .getGroupSubs();
        if (CollectionUtils.isEmpty(groupSubs)){
            message="Пока нет подписки на группы";

        }else {
            message="Чтобы удалить подписку передай - команду вместе с ID \n\n"+
                    "имя группы - ID группы \n\n"+
                    "%s";

        }
        String userGroupSubData=groupSubs.stream().map(group->String.format("%s - %s",group.getTitle(),group.getId()))
                .collect(Collectors.joining());
        sendBotMessageService.sendMessage(chatId,String.format(message,userGroupSubData));
    }
}
