package com.githab.javarushcommunity.javarush_telegrambot;

import com.githab.javarushcommunity.javarush_telegrambot.bot.JavaRushTelegramBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
@AutoConfigurationPackage

public abstract class JavarushTelegrambotApplication{

	public JavarushTelegrambotApplication(JavaRushTelegramBot bot) throws TelegramApiException {
	TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
	telegramBotsApi.registerBot(bot);
}



    public static void main(String[] args)  {


		SpringApplication.run(JavarushTelegrambotApplication.class, args);

		//new SpringApplicationBuilder(JavarushTelegrambotApplication.class).run(args);







	}

}