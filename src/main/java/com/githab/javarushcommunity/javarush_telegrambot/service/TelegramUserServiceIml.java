package com.githab.javarushcommunity.javarush_telegrambot.service;

import com.githab.javarushcommunity.javarush_telegrambot.repository.TelegramuserRepository;
import com.githab.javarushcommunity.javarush_telegrambot.repository.entity.TelegramUser;
import jakarta.annotation.Nonnull;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TelegramUserServiceIml implements TelegramUserService {

    public TelegramuserRepository telegramuserRepository;

    @Autowired
   public TelegramUserServiceIml(TelegramuserRepository telegramuserRepository) {
       this.telegramuserRepository = telegramuserRepository;
   }

    @Override

    public void save(TelegramUser telegramUser) {
        telegramuserRepository.save(telegramUser);
    }

    @Override

    public List<TelegramUser> retrieveAllActiveUser() {
        return telegramuserRepository.findAllByActiveTrue();
    }

    @Override

    public Optional<TelegramUser> findByChatId(String chatId) {
        return telegramuserRepository.findById(chatId);
    }
}
