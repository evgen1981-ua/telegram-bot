package com.githab.javarushcommunity.javarush_telegrambot.javarushclient;

import com.githab.javarushcommunity.javarush_telegrambot.javarushclient.dto.PostInfo;

import java.util.List;

public interface JavaRushPostClient {

    List<PostInfo> findNewPosts(Integer groupId, Integer lastPostId);
}
