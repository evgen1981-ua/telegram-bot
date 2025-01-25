import com.githab.javarushcommunity.javarush_telegrambot.command.Command;
import com.githab.javarushcommunity.javarush_telegrambot.command.StartCommand;
import org.junit.jupiter.api.DisplayName;

import static com.githab.javarushcommunity.javarush_telegrambot.command.CommandName.START;
import static com.githab.javarushcommunity.javarush_telegrambot.command.StartCommand.START_MESSAGE;
@DisplayName("Unit-level testing for StartCommand")
public class StartCommandTest extends AbstractCommandTest{
    @Override
    String getCommandName() {
        return START.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return START_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new StartCommand(sendBotMessageService,telegramUserService);
    }
}
