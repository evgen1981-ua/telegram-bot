package com.githab.javarushcommunity.javarush_telegrambot.command;

import com.githab.javarushcommunity.javarush_telegrambot.javarushclient.JavaRushGroupClient;
import com.githab.javarushcommunity.javarush_telegrambot.javarushclient.dto.GroupDiscussionInfo;
import com.githab.javarushcommunity.javarush_telegrambot.javarushclient.dto.GroupRequestArgs;
import com.githab.javarushcommunity.javarush_telegrambot.repository.entity.GroupSub;
import com.githab.javarushcommunity.javarush_telegrambot.service.GroupSubService;
import com.githab.javarushcommunity.javarush_telegrambot.service.SendBotMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.stream.Collectors;

import static com.githab.javarushcommunity.javarush_telegrambot.command.CommandName.ADD_GROUP_SUB;
import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.apache.commons.lang3.StringUtils.isNumeric;


public class AddGroupSubCommand implements Command {
    private final SendBotMessageService sendBotMessageService;
    private final JavaRushGroupClient javaRushGroupClient;
    private final GroupSubService groupSubService;

    public AddGroupSubCommand(SendBotMessageService sendBotMessageService, JavaRushGroupClient javaRushGroupClient,
                              GroupSubService groupSubService) {
        this.sendBotMessageService = sendBotMessageService;
        this.javaRushGroupClient = javaRushGroupClient;
        this.groupSubService = groupSubService;
    }

    @Override
    public void execute(Update update) {
        if (update.getMessage().getText().equalsIgnoreCase(ADD_GROUP_SUB.getCommandName())) {
            sendGroupIdList(String.valueOf(update.getMessage().getChatId()));

        }
        String groupId = update.getMessage().getText().split(SPACE)[1];
        String chatId = String.valueOf(update.getMessage().getChatId());
        if (isNumeric(groupId)) {
            GroupDiscussionInfo groupById = javaRushGroupClient.getGroupById(Integer.parseInt(groupId));

            if (isNull(groupById.getId())) {
                sendGroupNotFound(chatId, groupId);
            }
            GroupSub saveGroupSub = groupSubService.save(chatId, groupById);
            sendBotMessageService.sendMessage(chatId, "Підписав на групу " + saveGroupSub.getTitle());

        } else {
            sendGroupNotFound(chatId, groupId);
        }

    }
    private void sendGroupNotFound(String chatId,String groupId){
        String groupNotFoundMessage="Немає групи з ID=\"%s\"";
        sendBotMessageService.sendMessage(chatId,String.format(groupNotFoundMessage,groupId));
    }
    private void sendGroupIdList(String chatId){
        String groupIds=javaRushGroupClient.getGroupList(GroupRequestArgs.builder().build()).stream().map(group->
                String.format("%s-%s \n",group.getTitle(),group.getId())).collect(Collectors.joining());
        String message="Щоб підписатись на групу-передай передай команду разом з ID групи"+
                "я підготував список усіх груп-вибирай яку хочеш\n "+
                "им'я групи -ID групи\n"+"%s";
        sendBotMessageService.sendMessage(chatId,String.format(message,groupIds));
    }
}