package com.example.notificationservice.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConfig {

    @Bean
    public RestHighLevelClient client() {
        RestClientBuilder builder = RestClient.builder(
                new org.apache.http.HttpHost("localhost", 9200, "http")
        );
        return new RestHighLevelClient(builder);
    }
}
