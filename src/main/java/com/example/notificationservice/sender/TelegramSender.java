package com.example.notificationservice.sender;

import com.example.notificationservice.model.NotificationEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class TelegramSender implements NotificationSender {

    @Value("${telegram.bot.token}")
    private final String botToken = "7560953834:AAHc6w4SnC_378V3niH710DkgOddB9MSICc"; // Заменить на свой токен

    @Override
    public boolean supports(String channel) {
        return "telegram".equalsIgnoreCase(channel);
    }

    @Override
    public void send(NotificationEvent event) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.telegram.org/bot" + botToken + "/sendMessage";

        Map<String, String> params = new HashMap<>();
        params.put("chat_id", event.getAddress());
        params.put("text", event.getMessage());

        restTemplate.postForObject(url, params, String.class);
    }
}
