package com.githab.javarushcommunity.javarush_telegrambot.command;

import com.githab.javarushcommunity.javarush_telegrambot.command.annotation.AdminCommand;
import com.githab.javarushcommunity.javarush_telegrambot.javarushclient.JavaRushGroupClient;
import com.githab.javarushcommunity.javarush_telegrambot.service.GroupSubService;
import com.githab.javarushcommunity.javarush_telegrambot.service.SendBotMessageService;
import com.githab.javarushcommunity.javarush_telegrambot.service.TelegramUserService;
import com.google.common.collect.ImmutableMap;
import org.apache.http.conn.params.ConnConnectionParamBean;
import org.glassfish.grizzly.Connection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.githab.javarushcommunity.javarush_telegrambot.command.CommandName.*;
import static java.util.Objects.nonNull;

public class CommandContainer {
    public final ImmutableMap<String, Command> commandMap ;
    public final Command unknownCommand;
    private final List<String> admins;
    public CommandContainer(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService,
                            JavaRushGroupClient javaRushGroupClient, GroupSubService groupSubService,List<String> admins) {
        commandMap= ImmutableMap.<String, Command>builder().put(START.getCommandName(),new StartCommand(sendBotMessageService,telegramUserService))
                .put(HELP.getCommandName(),new HelpCommand(sendBotMessageService))
                .put(NO.getCommandName(),new NoCommand(sendBotMessageService))
                .put(STOP.getCommandName(),new StopCommand(sendBotMessageService,telegramUserService))
                .put(STAT.getCommandName(),new StatCommand(sendBotMessageService,telegramUserService))
                .put(ADD_GROUP_SUB.getCommandName(), new AddGroupSubCommand(sendBotMessageService,javaRushGroupClient,groupSubService))
                .put(LIST_GROUP_SUB.getCommandName(),new ListGroupSubCommand(sendBotMessageService,telegramUserService))
                .put(DELETE_GROUP_SUB.getCommandName(),new DeleteGroupSubCommand(sendBotMessageService,telegramUserService,groupSubService))
                .put(ADMIN_HELP.getCommandName(),new AdminHelpCommand(sendBotMessageService)).build();
        unknownCommand=new UnknownCommand(sendBotMessageService);
this.admins=admins;
    }

    public Command retrieveCommand(String commandIdentifier,String username) {
        Command orDefault = commandMap.getOrDefault(commandIdentifier, unknownCommand);
        if (isAdminCommand(orDefault)) {
            if (admins.contains(username)) {
                return orDefault;
            } else {
                return unknownCommand;
            }

        }
        return orDefault;
    }
    private boolean isAdminCommand (Command command){
        return nonNull(command.getClass().getAnnotation(AdminCommand.class));
    }
}

