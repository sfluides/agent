package fr.osb.smartf.agent.worker.service.util;

import com.thoughtworks.xstream.converters.ConversionException;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.item.file.transform.IncorrectTokenCountException;
import org.springframework.oxm.UnmarshallingFailureException;
import org.springframework.stereotype.Service;

/**
 * Created by szagoret on 10.08.2016.
 */
@Service
public class ImportFileMsgFormatExceptionServiceImpl implements ImportFileMsgFormatExceptionService {

    private static final String CONVERSION_ERROR_MESSAGE = "%s: [%s] [line number: %s] [required type: %s]";
    private static final String PARSING_ERROR_MESSAGE = "%s [message: %s] [line number: %s]";

    @Override
    public String getFormattedMessage(Exception e) {
        if (e instanceof UnmarshallingFailureException) {
            return unmarshallingFailureException((UnmarshallingFailureException) e);
        } else if (e instanceof FlatFileParseException) {
            return flatFileParseException((FlatFileParseException) e);
        }

        return e.getMessage();
    }

    private String flatFileParseException(FlatFileParseException e) {
        /**
         * IncorrectTokenCountException
         */
        if (e.getCause() instanceof IncorrectTokenCountException) {
            IncorrectTokenCountException countException = (IncorrectTokenCountException) e.getCause();
            return String.format(PARSING_ERROR_MESSAGE, e.getCause().getClass().getCanonicalName(),
                    countException.getMessage(), e.getLineNumber());
        }

        return e.getMessage();
    }

    private String unmarshallingFailureException(UnmarshallingFailureException e) {
        Throwable exceptionCause = e.getCause();

        /**
         * ConversionException
         */
        if (exceptionCause instanceof ConversionException) {
            ConversionException ce = (ConversionException) exceptionCause;
            return String.format(CONVERSION_ERROR_MESSAGE,
                    ce.get("cause-exception"),
                    ce.get("cause-message"),
                    ce.get("line number"),
                    ce.get("required-type"));
        }

        return e.getMessage();
    }
}
