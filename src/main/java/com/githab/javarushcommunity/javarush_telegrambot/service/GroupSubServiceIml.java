package com.githab.javarushcommunity.javarush_telegrambot.service;

import com.githab.javarushcommunity.javarush_telegrambot.javarushclient.dto.GroupDiscussionInfo;
import com.githab.javarushcommunity.javarush_telegrambot.repository.GroupSubRepository;
import com.githab.javarushcommunity.javarush_telegrambot.repository.entity.GroupSub;
import com.githab.javarushcommunity.javarush_telegrambot.repository.entity.TelegramUser;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroupSubServiceIml implements GroupSubService{
    private final GroupSubRepository groupSubRepository;
    private final TelegramUserService telegramUserService;

    @Autowired
    public GroupSubServiceIml(GroupSubRepository groupSubRepository,TelegramUserService telegramUserService) {
        this.groupSubRepository=groupSubRepository;
        this.telegramUserService=telegramUserService;
    }


    @Override
    public GroupSub save(String chatId, GroupDiscussionInfo groupDiscussionInfo) {
        TelegramUser telegramUser=telegramUserService.findByChatId(chatId).orElseThrow(NotFoundException::new);

        GroupSub groupSub;
        Optional<GroupSub> groupSubFromDB=groupSubRepository.findById(groupDiscussionInfo.getId());
        if (groupSubFromDB.isPresent()){
            groupSub=groupSubFromDB.get();
            Optional<TelegramUser> first=groupSub.getUsers().stream().filter(
                    it->it.getChat_id().equalsIgnoreCase(chatId)).findFirst();
            if (first.isEmpty()) {
                groupSub.addUser(telegramUser);
            }
            }else {
                groupSub=new GroupSub();
                groupSub.addUser(telegramUser);
                groupSub.setId(groupDiscussionInfo.getId());
                groupSub.setTitle(groupDiscussionInfo.getTitle());
            }
            return groupSubRepository.save(groupSub);
        }
    }

