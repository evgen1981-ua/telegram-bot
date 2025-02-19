package com.githab.javarushcommunity.javarush_telegrambot.javarushclient;

import com.githab.javarushcommunity.javarush_telegrambot.javarushclient.dto.GroupCountRequestArgs;
import com.githab.javarushcommunity.javarush_telegrambot.javarushclient.dto.GroupDiscussionInfo;
import com.githab.javarushcommunity.javarush_telegrambot.javarushclient.dto.GroupInfo;
import com.githab.javarushcommunity.javarush_telegrambot.javarushclient.dto.GroupRequestArgs;

import java.util.List;

public interface JavaRushGroupClient {
    List<GroupInfo>getGroupList(GroupRequestArgs requestArgs);

    List<GroupDiscussionInfo> getGroupDiscussionInfo(GroupRequestArgs requestArgs);

    Integer getGroupCount(GroupCountRequestArgs countRequestArgs);

    GroupDiscussionInfo getGroupById(Integer id);
    Integer findLastArticle(Integer groupSub);


}
