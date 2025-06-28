package com.example.notificationservice.kafka;

import com.example.notificationservice.elasticsearch.LogIndexer;
import com.example.notificationservice.model.LogEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class LogConsumer {

    @Autowired
    private LogIndexer logIndexer;

    private final ObjectMapper mapper = new ObjectMapper();

    @KafkaListener(topics = "logs-topic", groupId = "log-group")
    public void listen(ConsumerRecord<String, String> record) {
        try {
            LogEvent event = mapper.readValue(record.value(), LogEvent.class);
            logIndexer.indexLog(event);
            System.out.println("Лог сохранён в Elasticsearch");
        } catch (Exception e) {
            System.err.println("Ошибка обработки лога: " + e.getMessage());
        }
    }
}
