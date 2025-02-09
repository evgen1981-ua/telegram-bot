import com.githab.javarushcommunity.javarush_telegrambot.command.Command;
import com.githab.javarushcommunity.javarush_telegrambot.command.CommandContainer;
import com.githab.javarushcommunity.javarush_telegrambot.command.CommandName;
import com.githab.javarushcommunity.javarush_telegrambot.command.UnknownCommand;
import com.githab.javarushcommunity.javarush_telegrambot.javarushclient.JavaRushGroupClient;
import com.githab.javarushcommunity.javarush_telegrambot.service.GroupSubService;
import com.githab.javarushcommunity.javarush_telegrambot.service.SendBotMessageService;
import com.githab.javarushcommunity.javarush_telegrambot.service.TelegramUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

@DisplayName("Unit-level testing for CommandContainer")
public class CommandContainerTest {
    private CommandContainer commandContainer;

    @BeforeEach
    public void init(){
        SendBotMessageService sendBotMessageService= Mockito.mock(SendBotMessageService.class);
        TelegramUserService telegramUserService=Mockito.mock(TelegramUserService.class);
        JavaRushGroupClient javaRushGroupClient=Mockito.mock(JavaRushGroupClient.class);
        GroupSubService groupSubService=Mockito.mock(GroupSubService.class);
        commandContainer=new CommandContainer(sendBotMessageService,telegramUserService,javaRushGroupClient,groupSubService);
    }
    @Test
    public void shouldGetAllTheExistingCommands(){
        Arrays.stream(CommandName.values()).forEach(commandName -> {
            Command command=commandContainer.retrieveCommand(commandName.getCommandName());
            Assertions.assertNotEquals(UnknownCommand.class,command.getClass());
        });
    }
    @Test
    public void shouldReturnUnknownCommand(){
        String unknownCommand="/wggbvsr";
        Command command=commandContainer.retrieveCommand(unknownCommand);
        Assertions.assertEquals(UnknownCommand.class,command.getClass());
    }
}
