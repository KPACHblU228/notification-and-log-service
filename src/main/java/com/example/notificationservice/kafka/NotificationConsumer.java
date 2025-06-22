package com.example.notificationservice.kafka;

import com.example.notificationservice.model.NotificationEvent;
import com.example.notificationservice.sender.NotificationSender;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationConsumer {

    @Autowired
    private List<NotificationSender> senders;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "notification-topic", groupId = "notification-group")
    public void listen(ConsumerRecord<String, String> record) {
        try {
            String json = record.value();
            NotificationEvent event = objectMapper.readValue(json, NotificationEvent.class);

            for (String channel : event.getChannels()) {
                for (NotificationSender sender : senders) {
                    if (sender.supports(channel)) {
                        sender.send(event);
                        System.out.println("Уведомление отправлено через " + channel);
                    }
                }
            }

        } catch (Exception e) {
            System.err.println("Ошибка при обработке уведомления: " + e.getMessage());
        }
    }
}
