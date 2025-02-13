import com.githab.javarushcommunity.javarush_telegrambot.command.ListGroupSubCommand;
import com.githab.javarushcommunity.javarush_telegrambot.repository.entity.GroupSub;
import com.githab.javarushcommunity.javarush_telegrambot.repository.entity.TelegramUser;
import com.githab.javarushcommunity.javarush_telegrambot.service.SendBotMessageService;
import com.githab.javarushcommunity.javarush_telegrambot.service.TelegramUserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.githab.javarushcommunity.javarush_telegrambot.command.CommandName.LIST_GROUP_SUB;

@DisplayName("Unit-level testing for ListGroupSubCommand")
public class ListGroupSubCommandTest {

    @Test
    public void shouldProperlyShowsListGroupSub(){
        TelegramUser telegramUser=new TelegramUser();
        telegramUser.setActive(true);
        telegramUser.setChat_id("1");

        List<GroupSub> groupSubList=new ArrayList<>();
        groupSubList.add(populateGroupSub(1,"gs1"));
        groupSubList.add(populateGroupSub(2,"gs2"));
        groupSubList.add(populateGroupSub(3,"gs3"));
        groupSubList.add(populateGroupSub(4,"gs4"));

        telegramUser.setGroupSubs(groupSubList);

        SendBotMessageService sendBotMessageService= Mockito.mock(SendBotMessageService.class);
        TelegramUserService telegramUserService=Mockito.mock(TelegramUserService.class);

        Mockito.when(telegramUserService.findByChatId(telegramUser.getChat_id()))
                .thenReturn(Optional.of(telegramUser));
        ListGroupSubCommand command=new ListGroupSubCommand(sendBotMessageService,telegramUserService);

        Update update=new Update();
        Message message=Mockito.mock(Message.class);
        Mockito.when(message.getChatId()).thenReturn(Long.valueOf(telegramUser.getChat_id()));
        Mockito.when(message.getText()).thenReturn(LIST_GROUP_SUB.getCommandName());
update.setMessage(message);

String collectedGroups="Я знайшов усі підписки на групи: \n\n"+telegramUser.getGroupSubs().stream()
        .map(it->"група:"+it.getTitle()+" ,ID"+it.getId()+"\n").collect(Collectors.joining());

command.execute(update);

Mockito.verify(sendBotMessageService).sendMessage(String.valueOf(telegramUser.getChat_id()),collectedGroups);

    }
    private GroupSub populateGroupSub(Integer id,String title){
        GroupSub gs=new GroupSub();
        gs.setId(id);
        gs.setTitle(title);
        return gs;
    }

}
