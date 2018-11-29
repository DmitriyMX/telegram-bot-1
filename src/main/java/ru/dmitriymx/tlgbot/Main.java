package ru.dmitriymx.tlgbot;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import ru.dmitriymx.tlgbot.service.EchoService;

@Slf4j
public class Main {
    public static void main(String[] args) {
        EnvPropLogbackConfigurator.setup();
        ApiContextInitializer.init();
        TelegramBotsApi api = new TelegramBotsApi();

        TlgBot bot;

        if (Utils.getVariable("PROXY_HOST") != null) {
            final String PROXY_HOST = Utils.getVariable("PROXY_HOST");
            final int PROXY_PORT = Integer.parseInt(Utils.getVariable("PROXY_PORT"));

            log.debug("using proxy: {}:{}", PROXY_HOST, PROXY_PORT);

            DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);
            botOptions.setProxyHost(PROXY_HOST);
            botOptions.setProxyPort(PROXY_PORT);
            botOptions.setProxyType(DefaultBotOptions.ProxyType.SOCKS5);

            bot = new TlgBot(botOptions);
        } else {
            bot = new TlgBot();
        }

        bot.addService(new EchoService("Echo: "));

        try {
            log.trace("try api.registerBot;");
            api.registerBot(bot);
            log.trace("correct api.registerBot;");
        } catch (TelegramApiRequestException e) {
            log.error("Register bot error", e);
        }
    }
}
