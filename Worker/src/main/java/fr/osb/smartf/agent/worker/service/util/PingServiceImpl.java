package fr.osb.smartf.agent.worker.service.util;

import fr.osb.smartf.agent.worker.service.response.WorkerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;

/**
 * Created by szagoret on 15.06.2016.
 */
@Service
public class PingServiceImpl implements PingService {

    @Autowired
    DiscoveryClient discovery;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    WorkerStatusService workerStatusService;

    @Value("${worker.id}")
    private String workerId;

    /**
     * Get worker URI and available status
     *
     * @return PingResponse
     */
    @Override
    public WorkerInfo getWorkerStatus() {
        ServiceInstance si = discovery.getLocalServiceInstance();
        WorkerStatus workerStatus = workerStatusService.getWorkerStatus();
        URI wUri = si.getUri();
        return new WorkerInfo(
                workerId,
                wUri.getScheme(), wUri.getHost(),
                wUri.getPath(), wUri.getPort(),
                workerStatus);
    }
}
