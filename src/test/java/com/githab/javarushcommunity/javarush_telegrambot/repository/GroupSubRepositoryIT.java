package com.githab.javarushcommunity.javarush_telegrambot.repository;

import com.githab.javarushcommunity.javarush_telegrambot.repository.entity.GroupSub;
import com.githab.javarushcommunity.javarush_telegrambot.repository.entity.TelegramUser;
import org.checkerframework.checker.units.qual.A;
import org.hibernate.boot.spi.AdditionalJaxbMappingProducer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class GroupSubRepositoryIT {
    @Autowired
    private GroupSubRepository groupSubRepository;
    private TelegramuserRepository telegramuserRepository;

    @Sql(scripts = {"/sql/clearDbs.sql","/sql/fiveGroupSubsForUser.sql"})
    @Test
    public void shouldProperlyGetAllGroupSubsForUser(){
        Optional<TelegramUser> userFromDB=telegramuserRepository.findById("1");

        Assertions.assertTrue(userFromDB.isPresent());
        List<GroupSub> groupSubs=userFromDB.get().getGroupSubs();
        for (int i = 0; i < groupSubs.size(); i++) {
            Assertions.assertEquals(String.format("g%s",(i+1)),groupSubs.get(i).getTitle());
            Assertions.assertEquals(i+1,groupSubs.get(i).getId());
            Assertions.assertEquals(i+1,groupSubs.get(i).getLastArticleId());

        }
    }

    @Sql(scripts = {"/sql/clearDbs.sql","/sql/fiveUsersForGroupSub.sql"})
    @Test
    public void shouldProperlyGetAllUsersForGroupSub(){
        Optional<GroupSub> groupSubFromDB=groupSubRepository.findById(1);

        Assertions.assertTrue(groupSubFromDB.isPresent());
        Assertions.assertEquals(1,groupSubFromDB.get().getId());
        List<TelegramUser> users=groupSubFromDB.get().getUsers();
        for (int i = 0; i < users.size(); i++) {
            Assertions.assertEquals(String.valueOf(i+1),users.get(i).getChat_id());
            Assertions.assertTrue(users.get(i).isActive());

        }
    }

}
