package ru.dmitriymx.tlgbot.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.util.ArrayList;
import java.util.List;

public class KeyboardCommand extends AbstractBotCommand {
    private boolean showing = false;

    public KeyboardCommand() {
        super("key", "custom keyboard");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        SendMessage message = new SendMessage();
        message.setChatId(chat.getId());

        if (showing) {
            message.setText("Убираем клаву");
            message.setReplyMarkup(new ReplyKeyboardRemove());
            showing = false;
        } else  {
            List<KeyboardRow> keyboardRows = new ArrayList<>();

            KeyboardRow row = new KeyboardRow();
            row.add("Button 1-1");
            row.add("Button 1-2");
            row.add("Button 1-3");
            keyboardRows.add(row);

            row = new KeyboardRow();
            row.add("Button 2-1");
            row.add("Button 2-2");
            row.add("Button 2-3");
            keyboardRows.add(row);

            ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
            keyboardMarkup.setKeyboard(keyboardRows);

            message.setText("Вот тебе кастомная клава");
            message.setReplyMarkup(keyboardMarkup);
            showing = true;
        }

        sendMessage(absSender, message);
    }
}
