package com.githab.javarushcommunity.javarush_telegrambot.repository;

import com.githab.javarushcommunity.javarush_telegrambot.repository.entity.TelegramUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TelegramuserRepository extends CrudRepository<TelegramUser,String> {
List<TelegramUser> findAllByActiveTrue();
}
