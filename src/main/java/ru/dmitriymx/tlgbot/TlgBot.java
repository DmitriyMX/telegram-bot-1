package ru.dmitriymx.tlgbot;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.dmitriymx.tlgbot.service.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TlgBot extends TelegramLongPollingBot {
    private static final String TOKEN = Utils.getVariable("TLG_TOKEN");
    private static final String NAME  = Utils.getVariable("TLG_NAME");
    private List<Service> services = new ArrayList<>();

    static {
        log.debug("Bot name: {}", NAME);
        log.debug("Bot token: {}", TOKEN);
    }

    public void addService(Service service) {
        services.add(service);
    }

    @Override
    public void onUpdateReceived(Update update) {
        log.trace("onUpdateReceived.enter; update={}", update);

        services.forEach(service -> service.handle(new TelegramData(this, update)));

        log.trace("onUpdateReceived.exit; update={}", update);
    }

    @Override
    public String getBotUsername() {
        return NAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }
}
