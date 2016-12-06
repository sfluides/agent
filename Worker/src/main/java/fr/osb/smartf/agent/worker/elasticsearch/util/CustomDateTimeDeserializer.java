package fr.osb.smartf.agent.worker.elasticsearch.util;

import com.fasterxml.jackson.datatype.joda.cfg.FormatConfig;
import com.fasterxml.jackson.datatype.joda.deser.DateTimeDeserializer;
import org.joda.time.DateTime;

/**
 * Created by mpaltanea on 19.09.2016.
 */
public class CustomDateTimeDeserializer extends DateTimeDeserializer {
    public CustomDateTimeDeserializer() {
        super(DateTime.class, FormatConfig.DEFAULT_DATETIME_PARSER);
    }
}
