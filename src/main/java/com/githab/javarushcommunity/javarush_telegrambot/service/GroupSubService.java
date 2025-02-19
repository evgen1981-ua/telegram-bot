package com.githab.javarushcommunity.javarush_telegrambot.service;

import com.githab.javarushcommunity.javarush_telegrambot.javarushclient.dto.GroupDiscussionInfo;
import com.githab.javarushcommunity.javarush_telegrambot.repository.entity.GroupSub;

import java.util.List;
import java.util.Optional;

public interface GroupSubService {
    GroupSub save (String chatId, GroupDiscussionInfo groupDiscussionInfo);
    Optional<GroupSub> findById(Integer id);
    GroupSub save (GroupSub groupSub);
   List<GroupSub> findAll();
}
