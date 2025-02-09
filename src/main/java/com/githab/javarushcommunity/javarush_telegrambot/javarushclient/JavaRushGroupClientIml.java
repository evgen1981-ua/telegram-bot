package com.githab.javarushcommunity.javarush_telegrambot.javarushclient;

import com.githab.javarushcommunity.javarush_telegrambot.javarushclient.dto.GroupCountRequestArgs;
import com.githab.javarushcommunity.javarush_telegrambot.javarushclient.dto.GroupDiscussionInfo;
import com.githab.javarushcommunity.javarush_telegrambot.javarushclient.dto.GroupInfo;
import com.githab.javarushcommunity.javarush_telegrambot.javarushclient.dto.GroupRequestArgs;
import kong.unirest.GenericType;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class JavaRushGroupClientIml implements JavaRushGroupClient {

    private String javarushApiGroupPath;

    public JavaRushGroupClientIml(@Value("${javarush.api.path}") String javarushApi){
        this.javarushApiGroupPath=javarushApi+"/groups";
    }
    @Override
    public List<GroupInfo> getGroupList(GroupRequestArgs requestArgs) {
        return Unirest.get(javarushApiGroupPath).queryString(requestArgs.populateQueries()).
                asObject(new GenericType<List<GroupInfo>>() {
                }).getBody();

    }

    @Override
    public List<GroupDiscussionInfo> getGroupDiscussionInfo(GroupRequestArgs requestArgs) {
        return Unirest.get(javarushApiGroupPath).queryString(requestArgs.populateQueries()).
                asObject(new GenericType<List<GroupDiscussionInfo>>() {
                }).getBody();
    }

    @Override
    public Integer getGroupCount(GroupCountRequestArgs countRequestArgs) {
        return Integer.valueOf(Unirest.get(String.format("%s/count",javarushApiGroupPath)).
                queryString(countRequestArgs.populateQueries()).asString().getBody());
    }

    @Override
    public GroupDiscussionInfo getGroupById(Integer id) {
        return Unirest.get(String.format("%s/group%s",javarushApiGroupPath,id.toString())).
                asObject(GroupDiscussionInfo.class).getBody();
    }
}
