package fr.osb.smartf.agent.worker.batch.reading.processor;

import fr.osb.smartf.agent.worker.mongo.model.ReadingPdc;
import fr.osb.smartf.agent.worker.service.dto.ReadingPdcDTO;
import org.springframework.batch.item.ItemProcessor;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Created by szagoret on 12.10.2016.
 */
public class ReadingPdcItemProcessor implements ItemProcessor<ReadingPdcDTO, ReadingPdc> {

    @Override
    public ReadingPdc process(ReadingPdcDTO item) throws Exception {

        ReadingPdc readingPdc = new ReadingPdc();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime pdcIndexDateTime = LocalDateTime.parse(item.getDate(), formatter);
        readingPdc.setDate(pdcIndexDateTime);

        StringBuilder idIndexPdc = new StringBuilder();
        idIndexPdc.append(item.getIdPdc())
                .append(".")
                .append(pdcIndexDateTime.format(DateTimeFormatter.ofPattern("ddMMyyyHHmmss")))
                .append(".data");
        readingPdc.setId(idIndexPdc.toString());
        readingPdc.setIdPdc(item.getIdPdc());
        readingPdc.setIndex(NumberFormat.getInstance(Locale.FRANCE).parse(item.getValue()).doubleValue());

        return readingPdc;
    }
}
