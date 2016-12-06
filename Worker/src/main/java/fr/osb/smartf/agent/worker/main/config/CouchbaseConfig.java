package fr.osb.smartf.agent.worker.main.config;

/**
 * Created by mpaltanea on 13.06.2016.
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableCouchbaseRepositories(basePackages = {"fr.osb.smartf.agent.worker.couchbase.repository"})
public class CouchbaseConfig extends AbstractCouchbaseConfiguration {

    @Value("${couch.server.hosts}")
    private String hosts;

    @Value("${couch.server.bucket}")
    private String bucket;

    @Value("${couch.server.password}")
    private String password;

    @Override
    protected List<String> getBootstrapHosts() {
        return Arrays.asList(hosts);
    }

    @Override
    protected String getBucketName() {
        return bucket;
    }

    @Override
    protected String getBucketPassword() {
        return password;
    }


}
