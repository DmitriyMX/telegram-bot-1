package ru.dmitriymx.tlgbot;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@AllArgsConstructor
@Getter
@Setter
public class TelegramData {
    private TelegramLongPollingBot bot;
    private Update update;
}
