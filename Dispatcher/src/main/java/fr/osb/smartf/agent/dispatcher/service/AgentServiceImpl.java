package fr.osb.smartf.agent.dispatcher.service;

import fr.osb.smartf.agent.dispatcher.controller.dto.WorkerType;
import fr.osb.smartf.agent.dispatcher.exception.types.TechnicalException;
import fr.osb.smartf.agent.dispatcher.service.response.WorkerInfo;
import fr.osb.smartf.agent.dispatcher.service.response.WorkerStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.*;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.resource.OAuth2AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by szagoret on 06.06.2016.
 */
@Service
public class AgentServiceImpl implements AgentService {
    private static final Logger LOG = LoggerFactory.getLogger(AgentServiceImpl.class);

    private static final String NO_ACTIVE_AGENT_MSG = "No active agent found";
    private static final String NO_INSTANCES_MSG = "No instances found";
    private static final String AGENT_FOUND_MSG = "Found active agent";

    @Autowired
    private DiscoveryClient discovery;

    @Autowired
    private OAuth2RestOperations oauth2RestTemplate;

    @Autowired
    RestTemplate nonLoadBalancedRestTemplate;

    @Autowired
    @LoadBalanced
    RestTemplate loadBalancedRestTemplate;

    @Primary
    @Bean
    RestTemplate getNonLoadBalancedRestTemplate() {
        return new RestTemplate();
    }

    @LoadBalanced
    @Bean
    RestTemplate getLoadBalancedRestTemplate() {
        return new RestTemplate();
    }


    /**
     * Find active agent
     *
     * @return host:port of found agent
     */
    @Override
    public WorkerInfo getActiveWorker(WorkerType workerType) throws TechnicalException {

        int nrInstances = this.discovery.getInstances(workerType.name()).size();
        if (nrInstances == 0) {
            LOG.warn(NO_INSTANCES_MSG);
            throw new TechnicalException(NO_INSTANCES_MSG);
        }

        /**
         use the load balanced rest template to select an instance;
         the instance may still be registered in zookeeper, although its enclosed agent might be stopped
         retry selecting instance as many times as detected instances and until enclosed agent responds to ping
         */
        int instanceCounter = 0;

        WorkerInfo workerInfo;
        do {
            try {
                workerInfo = this.loadBalancedRestTemplate.getForObject("http://" + workerType.name() + "/ping", WorkerInfo.class);
                if (workerInfo.getStatus().equals(WorkerStatus.busy)) {
                    instanceCounter++;
                } else {
                    // found active agent that is not busy
                    LOG.info(AGENT_FOUND_MSG + " " + workerInfo);
                    return workerInfo;
                }
            } catch (Exception ex) {
                instanceCounter++;
                LOG.info(ex.getMessage());
            }
        } while (instanceCounter < nrInstances);
        // if no active agents found or all agents are busy throw an exception
        LOG.warn(NO_ACTIVE_AGENT_MSG);
        throw new TechnicalException(NO_ACTIVE_AGENT_MSG);
    }

    @Override
    public List<WorkerInfo> getWorkers(WorkerType workerType) throws TechnicalException {

        List<WorkerInfo> agents = new ArrayList<>();
        List<ServiceInstance> instances = this.discovery.getInstances(workerType.name());

        instances.forEach(item ->
        {
            try {
                WorkerInfo workerInfo = this.nonLoadBalancedRestTemplate.
                        getForObject(item.getUri() + "/ping", WorkerInfo.class);
                agents.add(workerInfo);
            } catch (OAuth2AccessDeniedException oauthException) {
                LOG.error("Access denied. Error requesting access token.");
            } catch (Exception ex) {
                LOG.warn("Worker " + item.getUri() + " " +
                        "is stopped but hasn't been unregistered yet from Zookeeper.");
            }
        });

        return agents;
    }

    public ResponseEntity<String> delegate(WorkerInfo workerInfo, WorkerType workerType, String workerMethod, Object param, MediaType mediaType) throws TechnicalException {
        workerInfo.setPath(workerInfo.getPath() + "/" + workerMethod);
        URI workerUri;
        try {
            workerUri = workerInfo.toUri();
        } catch (URISyntaxException e) {
            LOG.error(e.getMessage());
            //return Response.status(Response.Status.BAD_REQUEST).toString();
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        /**
         * set explicitly MediaType to make possible call JSON and XML services
         */
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(mediaType);

        HttpEntity<Object> httpEntity = new HttpEntity<>(param, httpHeaders);

        ResponseEntity<String> response = oauth2RestTemplate.exchange(workerUri, HttpMethod.POST, httpEntity, String.class);
        return response;
    }

    @Override
    public String getExternalResources() {
        return this.loadBalancedRestTemplate.getForObject("http://" + WorkerType.importer.name() + "/resourcesStatus", String.class);
    }
}
