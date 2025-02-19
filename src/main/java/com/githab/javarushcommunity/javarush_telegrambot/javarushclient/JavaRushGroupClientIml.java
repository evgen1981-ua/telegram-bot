package com.githab.javarushcommunity.javarush_telegrambot.javarushclient;

import com.githab.javarushcommunity.javarush_telegrambot.javarushclient.dto.*;
import kong.unirest.GenericType;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static org.springframework.util.CollectionUtils.isEmpty;

@Component
public class JavaRushGroupClientIml implements JavaRushGroupClient {

    private String javarushApiGroupPath;
    private String getJavarushApiPostPath;

    public JavaRushGroupClientIml(@Value("${javarush.api.path}") String javarushApi) {
        this.javarushApiGroupPath = javarushApi + "/groups";
        this.getJavarushApiPostPath = javarushApi + "/posts";
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


    @Override
        public Integer findLastArticle(Integer groupSubId) {
            List<PostInfo> posts = Unirest.get(getJavarushApiPostPath)
                    .queryString("order", "NEW")
                    .queryString("groupKid", groupSubId.toString())
                    .queryString("limit", "1")
                    .asObject(new GenericType<List<PostInfo>>() {
                    })
                    .getBody();
            return isEmpty(posts) ? 0 : Optional.ofNullable(posts.get(0)).map(PostInfo::getId).orElse(0);
    }
}
