package com.githab.javarushcommunity.javarush_telegrambot.command;

public enum CommandName {
    START("/start"),
    STOP("/stop"),
    HELP("/help"),
    NO(""),
    STAT("/stat");
    private final String commandName;
    CommandName (String commandName){
    this.commandName=commandName;

    }
    public String getCommandName(){
        return commandName;
    }
}
