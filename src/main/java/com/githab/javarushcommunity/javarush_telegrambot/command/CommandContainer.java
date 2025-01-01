package com.githab.javarushcommunity.javarush_telegrambot.command;

import com.githab.javarushcommunity.javarush_telegrambot.service.SendBotMessageService;
import com.google.common.collect.ImmutableMap;
import org.apache.http.conn.params.ConnConnectionParamBean;

import static com.githab.javarushcommunity.javarush_telegrambot.command.CommandName.*;

public class CommandContainer {
    public final ImmutableMap<String, Command> commandMap ;
    public final Command unknownCommand;

    public CommandContainer(SendBotMessageService sendBotMessageService) {
        commandMap= ImmutableMap.<String, Command>builder().put(START.getCommandName(),new StartCommand(sendBotMessageService))
                .put(HELP.getCommandName(),new HelpCommand(sendBotMessageService))
                .put(NO.getCommandName(),new NoCommand(sendBotMessageService))
        .put(STOP.getCommandName(),new StopCommand(sendBotMessageService)).build();
        unknownCommand=new UnknownCommand(sendBotMessageService);

    }
    public Command retrieveCommand(String commandIdentifier){
        return commandMap.getOrDefault(commandIdentifier,unknownCommand);
    }
}
