import com.githab.javarushcommunity.javarush_telegrambot.command.Command;
import com.githab.javarushcommunity.javarush_telegrambot.command.DeleteGroupSubCommand;
import com.githab.javarushcommunity.javarush_telegrambot.repository.entity.GroupSub;
import com.githab.javarushcommunity.javarush_telegrambot.repository.entity.TelegramUser;
import com.githab.javarushcommunity.javarush_telegrambot.service.GroupSubService;
import com.githab.javarushcommunity.javarush_telegrambot.service.SendBotMessageService;
import com.githab.javarushcommunity.javarush_telegrambot.service.TelegramUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.githab.javarushcommunity.javarush_telegrambot.command.CommandName.DELETE_GROUP_SUB;
import static java.util.Collections.singletonList;

@DisplayName("Unit-level testing for DeleteGroupSubCommandTest" )
public class DeleteGroupSubCommandTest {

    private Command command;
    private SendBotMessageService sendBotMessageService;
    GroupSubService groupSubService;
    TelegramUserService telegramUserService;

    @BeforeEach
    public void init(){
        sendBotMessageService= Mockito.mock(SendBotMessageService.class);
        groupSubService=Mockito.mock(GroupSubService.class);
        telegramUserService=Mockito.mock(TelegramUserService.class);

        command=new DeleteGroupSubCommand(sendBotMessageService,telegramUserService,groupSubService);

    }
    @Test
    public void shouldProperlyReturnEmptySubscriptionList(){
        Long chatId=23456L;

        Update update = AbstractCommandTest.prepareUpdate(chatId,DELETE_GROUP_SUB.getCommandName());
    Mockito.when(telegramUserService.findByChatId(String.valueOf(chatId))).thenReturn(Optional.of(new TelegramUser()));

    String expectedMessage="Пока нет подписки на группу";
    command.execute(update);
    Mockito.verify(sendBotMessageService).sendMessage(chatId.toString(),expectedMessage);

    }
    @Test
    public void shouldProperlyReturnSubscriptionLit() {

        Long chatId = 23456L;
        Update update = AbstractCommandTest.prepareUpdate(chatId, DELETE_GROUP_SUB.getCommandName());
        TelegramUser telegramUser = new TelegramUser();
        GroupSub gs1 = new GroupSub();
        gs1.setId(123);
        gs1.setTitle("GS1 Title");
        telegramUser.setGroupSubs(singletonList(gs1));
        Mockito.when(telegramUserService.findByChatId(String.valueOf(chatId)))
                .thenReturn(Optional.of(telegramUser));

        String expectedMessage = "Чтобы удалить подписку передай - команду вместе с ID \n\n"+
                "имя группы - ID группы \n\n"+
                "GS1 Title - 123";


        command.execute(update);


        Mockito.verify(sendBotMessageService).sendMessage(chatId.toString(), expectedMessage);
    }

    @Test
    public void shouldRejectByInvalidGroupId() {

        Long chatId = 23456L;
        Update update = AbstractCommandTest.prepareUpdate(chatId, String.format("%s %s", DELETE_GROUP_SUB.getCommandName(), "groupSubId"));
        TelegramUser telegramUser = new TelegramUser();
        GroupSub gs1 = new GroupSub();
        gs1.setId(123);
        gs1.setTitle("GS1 Title");
        telegramUser.setGroupSubs(singletonList(gs1));
        Mockito.when(telegramUserService.findByChatId(String.valueOf(chatId)))
                .thenReturn(Optional.of(telegramUser));

        String expectedMessage = "неправильный формат ID группы";



        command.execute(update);


        Mockito.verify(sendBotMessageService).sendMessage(chatId.toString(), expectedMessage);
    }

    @Test
    public void shouldProperlyDeleteByGroupId() {



        Long chatId = 23456L;
        Integer groupId = 1234;
        Update update = AbstractCommandTest.prepareUpdate(chatId, String.format("%s %s", DELETE_GROUP_SUB.getCommandName(), groupId));


        GroupSub gs1 = new GroupSub();
        gs1.setId(123);
        gs1.setTitle("GS1 Title");
        TelegramUser telegramUser = new TelegramUser();
        telegramUser.setChat_id(chatId.toString());
        telegramUser.setGroupSubs(singletonList(gs1));
        ArrayList<TelegramUser> users = new ArrayList<>();
        users.add(telegramUser);
        gs1.setUsers(users);
        Mockito.when(groupSubService.findById(groupId)).thenReturn(Optional.of(gs1));
        Mockito.when(telegramUserService.findByChatId(String.valueOf(chatId)))
                .thenReturn(Optional.of(telegramUser));

        String expectedMessage = "Удалил подписку на группу: GS1 Title";


        command.execute(update);


        users.remove(telegramUser);
        Mockito.verify(groupSubService).save(gs1);
        Mockito.verify(sendBotMessageService).sendMessage(chatId.toString(), expectedMessage);
    }

    @Test
    public void shouldDoesNotExistByGroupId() {

        Long chatId = 23456L;
        Integer groupId = 1234;
        Update update = AbstractCommandTest.prepareUpdate(chatId, String.format("%s %s", DELETE_GROUP_SUB.getCommandName(), groupId));


        Mockito.when(groupSubService.findById(groupId)).thenReturn(Optional.empty());

        String expectedMessage = "Не нашел такой группы =/";


        command.execute(update);


        Mockito.verify(groupSubService).findById(groupId);
        Mockito.verify(sendBotMessageService).sendMessage(chatId.toString(), expectedMessage);
    }
}

