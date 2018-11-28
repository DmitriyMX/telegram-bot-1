package ru.dmitriymx.tlgbot;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import ru.dmitriymx.tlgbot.service.EchoService;

@Slf4j
public class Main {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi api = new TelegramBotsApi();

        TlgBot bot = new TlgBot();
        bot.addService(new EchoService("Echo: "));

        try {
            log.trace("try api.registerBot;");
            api.registerBot(new TlgBot());
            log.trace("correct api.registerBot;");
        } catch (TelegramApiRequestException e) {
            log.error("Register bot error", e);
        }
    }
}
