package ru.dmitriymx.tlgbot.commands;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
public abstract class AbstractBotCommand extends BotCommand {

    public AbstractBotCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    public void sendMessage(AbsSender sender, Long chatId, String message) {
        SendMessage messageObj = new SendMessage();
        messageObj.setChatId(chatId);
        messageObj.setText(message);

        sendMessage(sender,messageObj);
    }

    public void sendMessage(AbsSender sender, SendMessage message) {
        try {
            sender.execute(message);
        } catch (TelegramApiException e) {
            log.error("Send message error", e);
        }
    }
}
