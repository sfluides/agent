package fr.osb.smartf.agent.worker.main.config;

import fr.osb.smartf.agent.worker.service.util.WorkerStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by szagoret on 15.06.2016.
 */
@Configuration
@ImportResource({"classpath:worker/worker-config.xml"})
public class WorkerConfig implements InitializingBean {


    public static final String SELECT_WORKER_STATUS = "SELECT WORKER_EXECUTION_STATUS FROM WORKER_EXECUTION WHERE WORKER_EXECUTION_ID = ?";
    public static final String INSERT_INITIAL_WORKER_STATUS = "INSERT INTO WORKER_EXECUTION (WORKER_EXECUTION_ID, WORKER_EXECUTION_STATUS) VALUES (?, ?)";
    public static final String UPDATE_WORKER_STATUS = "UPDATE WORKER_EXECUTION SET WORKER_EXECUTION_STATUS = ? WHERE WORKER_EXECUTION_ID = ?";

    public static final Long DEFAULT_ID = 1L;

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void afterPropertiesSet() throws Exception {
        /**
         * insert initial value for worker as "available"
         */
        jdbcTemplate.update(INSERT_INITIAL_WORKER_STATUS, DEFAULT_ID, WorkerStatus.available.name());
    }
}
