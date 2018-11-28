package ru.dmitriymx.tlgbot.service;

import ru.dmitriymx.tlgbot.TelegramData;

public interface Service {
    void handle(TelegramData data);
}
