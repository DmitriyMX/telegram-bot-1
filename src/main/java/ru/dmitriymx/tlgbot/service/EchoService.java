package ru.dmitriymx.tlgbot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.dmitriymx.tlgbot.TelegramData;

@Slf4j
@RequiredArgsConstructor
public class EchoService implements Service {
    private final String prefix;

    @Override
    public void handle(TelegramData data) {
        Update update = data.getUpdate();

        if (update.hasMessage() && update.getMessage().hasText()) {
            log.debug("Incoming message: {}", update.getMessage().getText());
            sendMessage(data.getBot(),
                        update.getMessage().getChatId(),
                        prefix + update.getMessage().getText());
        } else {
            log.debug("Unsupported type message");
        }
    }

    private void sendMessage(DefaultAbsSender sender, Long chatId, String message) {
        SendMessage messageObj = new SendMessage();
        messageObj.setChatId(chatId);
        messageObj.setText(message);

        try {
            sender.execute(messageObj);
        } catch (TelegramApiException e) {
            log.error("Send message error", e);
        }
    }

}
