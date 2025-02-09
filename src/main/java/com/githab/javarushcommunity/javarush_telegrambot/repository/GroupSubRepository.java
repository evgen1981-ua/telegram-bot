package com.githab.javarushcommunity.javarush_telegrambot.repository;

import com.githab.javarushcommunity.javarush_telegrambot.repository.entity.GroupSub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupSubRepository extends JpaRepository<GroupSub,Integer> {
}
