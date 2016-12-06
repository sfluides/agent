package fr.osb.smartf.agent.worker.batch.patrimony.processor;

import fr.osb.smartf.agent.worker.service.dto.patrimony.InstalledPatrimonyDTO;
import org.springframework.batch.item.ItemProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by mpaltanea on 20.07.2016.
 */
public class InstalledPatrimonyItemProcessor implements ItemProcessor<InstalledPatrimonyDTO, List<Object>> {

    @Override
    public List<Object> process(InstalledPatrimonyDTO patrimonyDTO) throws Exception {

        List<Object> items = new ArrayList<Object>();
        items.add(patrimonyDTO.getResidence());
        patrimonyDTO.getCountingPoints()
                .stream()
                .filter(Objects::nonNull)
                .forEach(item -> items.add(item));
        patrimonyDTO.getCounters()
                .stream()
                .filter(Objects::nonNull)
                .forEach(item -> items.add(item));
        return items;
    }
}