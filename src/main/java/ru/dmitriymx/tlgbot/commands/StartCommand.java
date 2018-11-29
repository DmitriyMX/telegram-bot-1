package ru.dmitriymx.tlgbot.commands;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Slf4j
public class StartCommand extends AbstractBotCommand {

    public StartCommand() {
        super("start", "Start bot");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String message = HelpCommand.getHelpMessage(user.getUserName());

        SendMessage messageObj = new SendMessage();
        messageObj.setChatId(chat.getId());
        messageObj.enableMarkdown(true);
        messageObj.setText(message);

        sendMessage(absSender, messageObj);
    }
}
