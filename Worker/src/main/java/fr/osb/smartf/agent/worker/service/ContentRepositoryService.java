package fr.osb.smartf.agent.worker.service;

import org.springframework.core.io.Resource;

/**
 * Created by mpaltanea on 02.08.2016.
 */
public interface ContentRepositoryService {
    public void uploadResource(Resource resource, String repoNode, String repoFile);
    public String getResource(String resourcePath);
    public void deleteResource(String resourcePath);
}
