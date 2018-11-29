# Telegram bot

## Возможности

- "повторюшка" (Echo)

## Сборка

```
mvn package
```

### Запуск

```
java -jar tlgbot.jar \
  -DTLG_TOKEN=123456:AbCdEfG \
  -DTLG_NAME=NameOfBot
```

Если используется proxy:

```
java -jar tlgbot.jar \
  -DTLG_TOKEN=123456:AbCdEfG \
  -DTLG_NAME=NameOfBot
  -DPROXY_HOST=127.0.0.1
  -DPROXY_PORT=9050
```
