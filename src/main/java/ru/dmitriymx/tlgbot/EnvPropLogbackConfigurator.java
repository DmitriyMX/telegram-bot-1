package ru.dmitriymx.tlgbot;

// https://stackoverflow.com/questions/23253916/is-it-possible-to-configure-logback-logger-levels-on-the-command-line/32603032#32603032

import ch.qos.logback.classic.Level;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

// for Heroku hosting
@Slf4j
public class EnvPropLogbackConfigurator {
    private static final String PROP_PREFIX = "LOG.";
    private static boolean init = false;

    public static void setup() {
        if (init) return;
        try {
            Class.forName("ch.qos.logback.classic.Logger");
        } catch (ClassNotFoundException e) {
            log.warn("Log provider is not \"logback\": class \"ch.qos.logback.classic.Logger\" not found");
            log.debug("Check Logback logger class", e);
            init = true;
            return;
        }

        System.getenv().keySet().stream()
                .filter(name -> name.startsWith(PROP_PREFIX))
                .forEach(EnvPropLogbackConfigurator::applyProp);
        init = true;
    }

    private static void applyProp(final String name) {
        String loggerName = name.substring(PROP_PREFIX.length());
        String loggerLevel = System.getenv(name);
        if (loggerLevel == null) {
            loggerLevel = "";
        }

        Level level = Level.toLevel(loggerLevel, null);
        ((ch.qos.logback.classic.Logger) LoggerFactory.getLogger(loggerName)).setLevel(level);
    }
}
