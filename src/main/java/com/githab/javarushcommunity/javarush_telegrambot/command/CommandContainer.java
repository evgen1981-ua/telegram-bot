package com.githab.javarushcommunity.javarush_telegrambot.command;

import com.githab.javarushcommunity.javarush_telegrambot.javarushclient.JavaRushGroupClient;
import com.githab.javarushcommunity.javarush_telegrambot.service.GroupSubService;
import com.githab.javarushcommunity.javarush_telegrambot.service.SendBotMessageService;
import com.githab.javarushcommunity.javarush_telegrambot.service.TelegramUserService;
import com.google.common.collect.ImmutableMap;
import org.apache.http.conn.params.ConnConnectionParamBean;
import org.springframework.beans.factory.annotation.Autowired;

import static com.githab.javarushcommunity.javarush_telegrambot.command.CommandName.*;

public class CommandContainer {
    public final ImmutableMap<String, Command> commandMap ;
    public final Command unknownCommand;
    public CommandContainer(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService, JavaRushGroupClient javaRushGroupClient, GroupSubService groupSubService) {
        commandMap= ImmutableMap.<String, Command>builder().put(START.getCommandName(),new StartCommand(sendBotMessageService,telegramUserService))
                .put(HELP.getCommandName(),new HelpCommand(sendBotMessageService))
                .put(NO.getCommandName(),new NoCommand(sendBotMessageService))
                .put(STOP.getCommandName(),new StopCommand(sendBotMessageService,telegramUserService))
                .put(STAT.getCommandName(),new StatCommand(sendBotMessageService,telegramUserService))
                .put(ADD_GROUP_SUB.getCommandName(), new AddGroupSubCommand(sendBotMessageService,javaRushGroupClient,groupSubService))
                .put(LIST_GROUP_SUB.getCommandName(),new ListGroupSubCommand(sendBotMessageService,telegramUserService)).build();
        unknownCommand=new UnknownCommand(sendBotMessageService);

    }
    public Command retrieveCommand(String commandIdentifier){
        return commandMap.getOrDefault(commandIdentifier,unknownCommand);
    }
}
