package fr.osb.smartf.agent.worker.batch.patrimony;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by mpaltanea on 13.07.2016.
 */
@Configuration
@ImportResource({
        "classpath:batch/patrimony/ImportPatrimonyXmlJob.xml"
})
public class ImportPatrimonyJobConfig {
    /**
     * Names of imported jobs
     */
    public static final String IMPORT_BUILT_PATRIMONY_XML = "importBuiltPatrimonyXmlJob";
    public static final String IMPORT_INSTALLED_PATRIMONY_XML = "importInstalledPatrimonyXmlJob";

}
