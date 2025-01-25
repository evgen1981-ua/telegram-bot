import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.githab.javarushcommunity.javarush_telegrambot.repository.TelegramuserRepository;
import com.githab.javarushcommunity.javarush_telegrambot.repository.entity.TelegramUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;



@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase
public class TelegramUserRepositoryIT {


    @Autowired
    private TelegramuserRepository telegramuserRepository;

    @Sql (scripts = {"/sql/clearDbs.sql","/sql/telegram_users.sql"})
    @Test
    public void shouldProperlyFindAllActiveUsers(){
        List<TelegramUser> users=telegramuserRepository.findAllByActiveTrue();
        Assertions.assertEquals(5,users.size());

    }
    @Sql(scripts = {"/sql/clearDbs.sql"})
    @Test
    public void shouldProperlySaveTelegramUser(){
      TelegramUser telegramUser=new TelegramUser();
      telegramUser.setChat_id("1234567890");
      telegramUser.setActive(false);
      telegramuserRepository.save(telegramUser);

        Optional<TelegramUser> saved=telegramuserRepository.findById(telegramUser.getChat_id());

        Assertions.assertTrue(saved.isPresent());
        Assertions.assertEquals(telegramUser,saved.get());
    }

}
