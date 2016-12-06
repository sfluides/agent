package fr.osb.smartf.agent.worker.main.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by mpaltanea on 14.09.2016.
 */
@Configuration
@EnableElasticsearchRepositories({"fr.osb.smartf.agent.worker.elasticsearch.repository"})
public class ElasticsearchConfig {

    /*@Value("${spring.data.elastic.host}")
    private String host;

    @Value("${spring.data.elastic.port}")
    private int port;

    @Bean
    public Client client() {
        TransportClient client = new TransportClient();
        TransportAddress address = new InetSocketTransportAddress(host, 9300);
        client.addTransportAddress(address);
        return client;
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchTemplate(client());
    }*/

    @Value("${spring.data.elasticsearch.cluster-name}")
    private String clusterName;
    @Value("${spring.data.elasticsearch.cluster-nodes}")
    private String clusterNodes;
    @Bean
    public ElasticsearchTemplate elasticsearchTemplate() throws UnknownHostException {
        String server = clusterNodes.split(":")[0];
        Integer port = Integer.parseInt(clusterNodes.split(":")[1]);
        Settings settings = Settings.settingsBuilder()
                .put("cluster.name", clusterName).build();
        TransportClient client = TransportClient.builder().settings(settings).build()
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(server), port));
        return new ElasticsearchTemplate(client);

    }

}
