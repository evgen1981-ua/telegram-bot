import com.githab.javarushcommunity.javarush_telegrambot.javarushclient.dto.GroupStatDTO;
import com.githab.javarushcommunity.javarush_telegrambot.javarushclient.dto.StatisticDTO;
import com.githab.javarushcommunity.javarush_telegrambot.repository.entity.GroupSub;
import com.githab.javarushcommunity.javarush_telegrambot.repository.entity.TelegramUser;
import com.githab.javarushcommunity.javarush_telegrambot.service.GroupSubService;
import com.githab.javarushcommunity.javarush_telegrambot.service.StatisticsService;
import com.githab.javarushcommunity.javarush_telegrambot.service.TelegramUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import java.util.Collections;

import static java.util.Collections.singletonList;

public class StatisticsServiceTest {
    private GroupSubService groupSubService;
    private TelegramUserService telegramUserService;

    private StatisticsService statisticsService;

    @BeforeEach
    public void init(){
        groupSubService= Mockito.mock(GroupSubService.class);
        telegramUserService=Mockito.mock(TelegramUserService.class);
        statisticsService=Mockito.mock(StatisticsService.class);

    }
    public void shouldProperlySendStatDTO(){
        Mockito.when(telegramUserService.findAllInActiveUsers()).thenReturn(singletonList(new TelegramUser()));
        TelegramUser activeUser=new TelegramUser();
        activeUser.setGroupSubs(singletonList(new GroupSub()));
        Mockito.when(telegramUserService.findAllActiveUser()).thenReturn(singletonList(activeUser));
        GroupSub groupSub=new GroupSub();
        groupSub.setTitle("group");
        groupSub.setId(1);
        groupSub.setUsers(singletonList(new TelegramUser()));
        Mockito.when(groupSubService.findAll()).thenReturn(singletonList(groupSub));

        StatisticDTO statisticDTO=statisticsService.countBotStatistic();

        Assertions.assertNotNull(statisticDTO);
        Assertions.assertEquals(1,statisticDTO.getActiveUserCount());
        Assertions.assertEquals(1,statisticDTO.getInactiveUserCount());
        Assertions.assertEquals(1.0,statisticDTO.getAverageGroupCountByUser());
        Assertions.assertEquals(singletonList(new GroupStatDTO(groupSub.getId(),groupSub.getTitle(),groupSub.getUsers().size())),
                statisticDTO.getGroupStatDTOs());
    }
}
