import com.githab.javarushcommunity.javarush_telegrambot.javarushclient.JavaRushPostClient;
import com.githab.javarushcommunity.javarush_telegrambot.javarushclient.JavaRushPostClientIml;
import com.githab.javarushcommunity.javarush_telegrambot.javarushclient.dto.PostInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class JavaRushPostClientTest {

    private final JavaRushPostClient postClient=new JavaRushPostClientIml("https://javarush.com/api/1.0/rest");

    @Test
    public void shouldProperlyGetNew15Posts(){
        List<PostInfo> newPosts=postClient.findNewPosts(30,2935);
        Assertions.assertEquals(15,newPosts.size());

    }



}
