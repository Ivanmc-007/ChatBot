package com.ivan.application.bot;

import com.ivan.application.entity.City;
import com.ivan.application.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Optional;

@Component
@PropertySource("classpath:telegram.properties")
public class MyChatBot extends TelegramLongPollingBot {

    private final static Logger LOG = LoggerFactory.getLogger(MyChatBot.class);

    @Value("${telegram.botName}")
    private String botName;

    @Value("${telegram.botToken}")
    private String botToken;

    @Override
    public String getBotToken() {
        return botToken;
    }

    private final CityService cityService;

    public MyChatBot(CityService cityService) {
        this.cityService = cityService;
    }

    private void sendMessage(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        // в какой чат мы отправляем сообщение
        sendMessage.setChatId(String.valueOf(message.getChatId()));
        // на какое сообщение отвечаем
        sendMessage.setReplyToMessageId(message.getMessageId());
        // что отвечаем
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            LOG.error("LOGGER: catch " + e + " in " + getClass().getName());
            return;
        }
        LOG.info("LOGGER: message-answer sent to user!");
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message;
        if(update.hasMessage() && (message = update.getMessage()).hasText()) {
            if(!"/start".equals(message.getText())) {
                String textFrom = update.getMessage().getText();
                Optional<City> optional;
                String textAnswer;
                if ((optional = cityService.findByName(textFrom)).isPresent()) {
                    textAnswer = optional.get().getInformation();
                } else {
                    textAnswer = "Я не знаю такого города!";
                }
                sendMessage(message, textAnswer);
            } else {
                sendMessage(message, "Hello! " +
                    "I am a bot guide. " +
                    "Tell me the name of the city and I will answer you something interesting!");
            }
        }
    }

    @Override
    public String getBotUsername() {
        return botName;
    }
}
