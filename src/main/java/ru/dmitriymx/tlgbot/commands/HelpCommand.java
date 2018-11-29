package ru.dmitriymx.tlgbot.commands;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Slf4j
public class HelpCommand extends AbstractBotCommand {
    public static String getHelpMessage(String userName) {
        return "**Привет, " + userName + "**\n"
                + "Этот бот пока что мало чего умеет.\n"
                + "Может за тобой повторять и показывать эту справку через комманду /help.";
    }

    public HelpCommand() {
        super("help", "Help page");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String message = getHelpMessage(user.getUserName());

        SendMessage messageObj = new SendMessage();
        messageObj.setChatId(chat.getId());
        messageObj.enableMarkdown(true);
        messageObj.setText(message);

        sendMessage(absSender, messageObj);
    }
}
