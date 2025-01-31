package com.betterjavacode.elasticsearchdemo.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.http.HttpHeaders;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.betterjavacode.elasticsearchdemo.repositories")
@ComponentScan(basePackages = "com.betterjavacode.elasticsearchdemo")
public class ElasticsearchClientConfiguration extends AbstractElasticsearchConfiguration
{
    private static final String USER_NAME = "elastic";
    private static final String USER_PASS = "Tct0Pr0Ptu7FYBNwSg3A";

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient ()
    {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.ACCEPT, "application/vnd.elasticsearch+json;compatible-with=7");
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/vnd.elasticsearch+json;compatible-with=7");

        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("localhost:9200")  // set the address of the Elasticsearch cluster
                // .usingSsl(createSSLContext())  // use the SSLContext with the client cert
                .withBasicAuth(USER_NAME, USER_PASS)   // use the headers for authentication
                .withDefaultHeaders(httpHeaders)
                .build();

        return RestClients.create(clientConfiguration).rest();
    }
}
