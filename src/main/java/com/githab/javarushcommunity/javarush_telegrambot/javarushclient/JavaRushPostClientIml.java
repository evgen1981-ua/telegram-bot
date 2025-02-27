package com.githab.javarushcommunity.javarush_telegrambot.javarushclient;

import com.githab.javarushcommunity.javarush_telegrambot.javarushclient.dto.PostInfo;
import kong.unirest.GenericType;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class JavaRushPostClientIml implements JavaRushPostClient{

    private final String javarushApiPostPath;

    public JavaRushPostClientIml(@Value("${javarush.api.path}") String javarushApi){
        this.javarushApiPostPath=javarushApi+"/posts";
    }
    @Override
    public List<PostInfo> findNewPosts(Integer groupId, Integer lastArticleId) {
        List<PostInfo> lastPostsByGroup= Unirest.get(javarushApiPostPath)
                .queryString("order","NEW")
                .queryString("groupKid",groupId)
                .queryString("limit",15)
                .asObject(new GenericType<List<PostInfo>>() {
                }).getBody();
        List<PostInfo> newPosts=new ArrayList<>();
        for (PostInfo post:lastPostsByGroup){
            if(lastArticleId.equals(post.getId())){
                return newPosts;
            }
            newPosts.add(post);
        }

        return newPosts;
    }
}
