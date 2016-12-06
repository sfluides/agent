package fr.osb.smartf.agent.worker.batch.reading;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by mpaltanea on 09.06.2016.
 */
@Configuration
@ImportResource({
        "classpath:batch/reading/ImportReadingSupport.xml",
        "classpath:batch/reading/ImportReadingCsvJob.xml",
        "classpath:batch/reading/ImportReadingExcelJob.xml",
        "classpath:batch/reading/ImportReadingXmlJob.xml"
})
public class ImportReadingJobConfig {
    /**
     * Names of imported jobs
     */
    public static final String IMPORT_READING_CSV = "importReadingCSVJob";
    public static final String IMPORT_READING_EXCEL = "importReadingExcelJob";
    public static final String IMPORT_READING_XML = "importReadingXmlJob";
}
