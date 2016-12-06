package fr.osb.smartf.agent.worker.batch.reading;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by szagoret on 22.06.2016.
 */
@Configuration
@ImportResource({
        "classpath:batch/reading/CalculateConsumptionJob.xml"
})
public class CalculateConsumptionJobConfig {
    /**
     * Names of imported jobs
     */
    public static final String CALCUL_CONSUM = "calculConsumJob";
}
