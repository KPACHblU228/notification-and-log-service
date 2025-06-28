package com.example.notificationservice.elasticsearch;

import com.example.notificationservice.model.LogEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LogIndexer {

    @Autowired
    private RestHighLevelClient client;

    private final ObjectMapper mapper = new ObjectMapper();

    public void indexLog(LogEvent event) {
        try {
            Map<String, Object> jsonMap = mapper.convertValue(event, Map.class);
            IndexRequest request = new IndexRequest("logs-index")
                    .source(jsonMap);
            client.index(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            System.err.println("Ошибка индексирования лога: " + e.getMessage());
        }
    }
}
