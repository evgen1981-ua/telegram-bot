import com.githab.javarushcommunity.javarush_telegrambot.command.Command;
import com.githab.javarushcommunity.javarush_telegrambot.command.StopCommand;
import org.junit.jupiter.api.DisplayName;

import static com.githab.javarushcommunity.javarush_telegrambot.command.CommandName.STOP;
import static com.githab.javarushcommunity.javarush_telegrambot.command.StopCommand.END_MESSAGE;
@DisplayName("Unit-level testing for StopCommand")
public class StopCommandTest extends AbstractCommandTest{
    @Override
    String getCommandName() {
        return STOP.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return END_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new StopCommand(sendBotMessageService);
    }
}
