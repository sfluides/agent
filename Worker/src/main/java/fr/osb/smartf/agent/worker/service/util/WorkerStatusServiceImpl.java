package fr.osb.smartf.agent.worker.service.util;

import fr.osb.smartf.agent.worker.main.config.WorkerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by szagoret on 15.06.2016.
 */
@Service
public class WorkerStatusServiceImpl implements WorkerStatusService {

    Logger LOG = LoggerFactory.getLogger(WorkerStatusServiceImpl.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void updateWorkerStatus(WorkerStatus workerStatus) {
        LOG.info("SET WORKER STATUS : " + workerStatus.name());
        jdbcTemplate.update(WorkerConfig.UPDATE_WORKER_STATUS, workerStatus.name(), WorkerConfig.DEFAULT_ID);
    }

    @Override
    public WorkerStatus getWorkerStatus() {
        return WorkerStatus.valueOf(
                jdbcTemplate.queryForObject(WorkerConfig.SELECT_WORKER_STATUS, new Object[]{WorkerConfig.DEFAULT_ID},
                        String.class));
    }

}
