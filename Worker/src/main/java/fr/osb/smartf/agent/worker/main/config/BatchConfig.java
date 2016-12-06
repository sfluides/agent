package fr.osb.smartf.agent.worker.main.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by szagoret on 06.06.2016.
 */
@Configuration
@ImportResource({
        "classpath:batch/batch-config.xml",
        "classpath:batch/AbstractParentJob.xml"
})
public class BatchConfig {
}
