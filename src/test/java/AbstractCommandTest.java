import com.githab.javarushcommunity.javarush_telegrambot.bot.JavaRushTelegramBot;
import com.githab.javarushcommunity.javarush_telegrambot.command.Command;
import com.githab.javarushcommunity.javarush_telegrambot.service.SendBotMessageImpl;
import com.githab.javarushcommunity.javarush_telegrambot.service.SendBotMessageService;
import com.githab.javarushcommunity.javarush_telegrambot.service.TelegramUserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public abstract class AbstractCommandTest {
    protected TelegramUserService telegramUserService=Mockito.mock(TelegramUserService.class);
    protected JavaRushTelegramBot javaRushBot= Mockito.mock(JavaRushTelegramBot.class);
    protected SendBotMessageService sendBotMessageService=new SendBotMessageImpl(javaRushBot);

    abstract String getCommandName();
    abstract String getCommandMessage();
    abstract Command getCommand();

    @Test
    public void shouldProperlyExecuteCommand() throws TelegramApiException{
Long chatId=1234567823467L;

        Update update=new Update();
        Message message=Mockito.mock(Message.class);
        Mockito.when(message.getChatId()).thenReturn(chatId);
        Mockito.when(message.getText()).thenReturn(getCommandName());
        update.setMessage(message);

        SendMessage sendMessage=new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(getCommandMessage());
        sendMessage.enableHtml(true);

        getCommand().execute(update);

        Mockito.verify(javaRushBot).execute(sendMessage);
    }

}
