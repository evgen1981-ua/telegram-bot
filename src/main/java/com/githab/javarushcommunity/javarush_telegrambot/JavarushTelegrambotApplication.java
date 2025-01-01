package com.githab.javarushcommunity.javarush_telegrambot;

import com.githab.javarushcommunity.javarush_telegrambot.bot.JavaRushTelegramBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.telegram.telegrambots.meta.ApiConstants;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public abstract class JavarushTelegrambotApplication {

	public static void main(String[] args) throws TelegramApiException {
		TelegramBotsApi telegramBotsApi=new TelegramBotsApi(DefaultBotSession.class);
		try{
			telegramBotsApi.registerBot(new JavaRushTelegramBot());
		}catch (TelegramApiRequestException e){

		}
		SpringApplication.run(JavarushTelegrambotApplication.class, args);
	}

}