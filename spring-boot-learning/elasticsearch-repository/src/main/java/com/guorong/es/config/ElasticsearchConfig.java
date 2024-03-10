package com.guorong.es.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.ElasticsearchConfigurationSupport;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetSocketAddress;

/**
 * @author guorong
 * @date 2021-04-15
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.guorong.es.dao")
public class ElasticsearchConfig extends ElasticsearchConfigurationSupport {


    @Bean
    public RestHighLevelClient client() {
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 9200);
        ClientConfiguration clientConfiguration
                = ClientConfiguration.builder()
                .connectedTo(inetSocketAddress)
                .build();
        return RestClients.create(clientConfiguration).rest();
    }


    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchRestTemplate(client());
    }
}


