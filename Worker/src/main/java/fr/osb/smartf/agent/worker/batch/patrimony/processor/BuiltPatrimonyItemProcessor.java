package fr.osb.smartf.agent.worker.batch.patrimony.processor;

import fr.osb.smartf.agent.worker.service.dto.patrimony.BuiltPatrimonyDTO;
import org.springframework.batch.item.ItemProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by szagoret on 19.07.2016.
 */
public class BuiltPatrimonyItemProcessor implements ItemProcessor<BuiltPatrimonyDTO, List<Object>> {

    @Override
    public List<Object> process(BuiltPatrimonyDTO patrimonyDTO) throws Exception {

        List<Object> items = new ArrayList<Object>();
        items.add(patrimonyDTO.getResidence());
        patrimonyDTO.getBuildings()
                .stream()
                .filter(Objects::nonNull)
                .forEach(item -> items.add(item));
        patrimonyDTO.getHousings()
                .stream()
                .filter(Objects::nonNull)
                .forEach(item -> items.add(item));
        patrimonyDTO.getLocals()
                .stream()
                .filter(Objects::nonNull)
                .forEach(item -> items.add(item));
        return items;
    }
}
