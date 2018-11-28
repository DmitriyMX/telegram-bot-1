package ru.dmitriymx.tlgbot;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
public class TlgBot extends TelegramLongPollingBot {
    private static final String TOKEN = System.getenv("TLG_TOKEN");
    private static final String NAME  = System.getenv("TLG_NAME");

    @Override
    public void onUpdateReceived(Update update) {
        log.trace("onUpdateReceived.enter; update={}", update);

        if (update.hasMessage() && update.getMessage().hasText()) {
            log.debug("Incoming message: {}", update.getMessage().getText());
            sendMessage(update.getMessage().getChatId(),
                        "Echo: " + update.getMessage().getText());
        } else {
            log.debug("Unsupported type message");
        }

        log.trace("onUpdateReceived.exit; update={}", update);
    }

    private void sendMessage(Long chatId, String message) {
        SendMessage messageObj = new SendMessage();
        messageObj.setChatId(chatId);
        messageObj.setText(message);

        try {
            execute(messageObj);
        } catch (TelegramApiException e) {
            log.error("Send message error", e);
        }
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
