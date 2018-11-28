package ru.dmitriymx.tlgbot;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.dmitriymx.tlgbot.commands.HelpCommand;
import ru.dmitriymx.tlgbot.commands.StartCommand;
import ru.dmitriymx.tlgbot.service.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TlgBot extends TelegramLongPollingCommandBot {
    private static final String TOKEN = Utils.getVariable("TLG_TOKEN");
    private static final String NAME  = Utils.getVariable("TLG_NAME");
    private List<Service> services = new ArrayList<>();

    static {
        log.debug("Bot name: {}", NAME);
        log.debug("Bot token: {}", TOKEN);
    }

    public TlgBot() {
        super(NAME);

        register(new StartCommand());
        register(new HelpCommand());
    }

    public void addService(Service service) {
        services.add(service);
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        log.trace("processNonCommandUpdate.enter; update={}", update);

        services.forEach(service -> service.handle(new TelegramData(this, update)));

        log.trace("processNonCommandUpdate.exit; update={}", update);
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }
}
