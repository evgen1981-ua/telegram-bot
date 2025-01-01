import com.githab.javarushcommunity.javarush_telegrambot.command.Command;
import com.githab.javarushcommunity.javarush_telegrambot.command.HelpCommand;
import org.junit.jupiter.api.DisplayName;

import static com.githab.javarushcommunity.javarush_telegrambot.command.CommandName.HELP;
import static com.githab.javarushcommunity.javarush_telegrambot.command.HelpCommand.HELP_MESSAGE;


@DisplayName("Unit-level testing for HelpCommand")

public class HelpCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return HELP.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return HELP_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new HelpCommand(sendBotMessageService);
    }
}
