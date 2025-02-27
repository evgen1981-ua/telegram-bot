package com.githab.javarushcommunity.javarush_telegrambot.service;

import com.githab.javarushcommunity.javarush_telegrambot.javarushclient.dto.GroupStatDTO;
import com.githab.javarushcommunity.javarush_telegrambot.javarushclient.dto.StatisticDTO;
import com.githab.javarushcommunity.javarush_telegrambot.repository.entity.TelegramUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.CollectionUtils.isEmpty;

@Service
public class StatisticsServiceIml implements StatisticsService{

    private final GroupSubService groupSubService;
    private final TelegramUserService telegramUserService;

    public StatisticsServiceIml(GroupSubService groupSubService, TelegramUserService telegramUserService) {
        this.groupSubService = groupSubService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public StatisticDTO countBotStatistic() {
        List<GroupStatDTO> groupStatDTOS=groupSubService.findAll().stream()
                .filter(it->!isEmpty(it.getUsers()))
                .map(groupSub -> new GroupStatDTO(groupSub.getId(),groupSub.getTitle(),groupSub.getUsers().size()))
                .toList();
        List<TelegramUser> allInActiveUsers=telegramUserService.findAllInActiveUsers();
        List<TelegramUser> allActiveUsers=telegramUserService.findAllActiveUser();

        double groupsPerUser=getGroupPerUser(allActiveUsers);

        return new StatisticDTO(allActiveUsers.size(),allInActiveUsers.size(),groupStatDTOS,groupsPerUser);
    }
    private double getGroupPerUser(List<TelegramUser> allActiveUsers){
        return (double) allActiveUsers.stream().mapToInt(it->it.getGroupSubs().size()).sum()/allActiveUsers.size();

    }
}
