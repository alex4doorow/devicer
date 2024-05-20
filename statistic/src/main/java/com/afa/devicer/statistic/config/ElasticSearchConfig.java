package com.afa.devicer.statistic.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponseInterceptor;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchClients;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

//@Configuration
//@EnableElasticsearchRepositories(basePackages = "com.afa.devicer.statistic.es.aggregation.repositories")
//@ComponentScan(basePackages = "com.afa.devicer.statistic.es.aggregation.model")
public class ElasticSearchConfig {

    @Bean
    public RestClient elasticsearchRestClient() {
        return RestClient.builder(HttpHost.create("localhost:9200"))
                .setHttpClientConfigCallback(httpClientBuilder -> {
                    httpClientBuilder.addInterceptorLast((HttpResponseInterceptor) (response, context) ->
                            response.addHeader("X-Elastic-Product", "Elasticsearch"));
                    return httpClientBuilder;
                })
                .build();
    }

    /*
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .build();

        return RestClients.create(clientConfiguration)
                .rest();
    }
    */

    @Bean
    public ElasticsearchClient elasticsearchClient(RestClient restClient) {
        return ElasticsearchClients.createImperative(restClient);
    }

    @Bean(name = {"elasticsearchOperations", "elasticsearchTemplate"})
    public ElasticsearchOperations elasticsearchOperations(
            ElasticsearchClient elasticsearchClient) {

        ElasticsearchTemplate template = new ElasticsearchTemplate(elasticsearchClient);
        template.setRefreshPolicy(null);

        return template;
    }
}
