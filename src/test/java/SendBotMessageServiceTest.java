import com.githab.javarushcommunity.javarush_telegrambot.bot.JavaRushTelegramBot;
import com.githab.javarushcommunity.javarush_telegrambot.service.SendBotMessageImpl;
import com.githab.javarushcommunity.javarush_telegrambot.service.SendBotMessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@DisplayName("Unit-level testing for SendBotMessageService")
public class SendBotMessageServiceTest {
private SendBotMessageService sendBotMessageService;
private JavaRushTelegramBot javaRushBot;

@BeforeEach
    public void init(){
    javaRushBot= Mockito.mock(JavaRushTelegramBot.class);
    sendBotMessageService=new SendBotMessageImpl(javaRushBot);
}
@Test
    public void shouldProperlySendMessage() throws TelegramApiException{
    String chatId="test_chat_id";
    String message="test_message";

    SendMessage sendMessage=new SendMessage();
    sendMessage.setText(message);
    sendMessage.setChatId(chatId);
    sendMessage.enableHtml(true);

    sendBotMessageService.sendMessage(chatId,message);
    Mockito.verify(javaRushBot).execute(sendMessage);
}
}
