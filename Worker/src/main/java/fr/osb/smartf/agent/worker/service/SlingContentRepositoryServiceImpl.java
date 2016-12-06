package fr.osb.smartf.agent.worker.service;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

/**
 * Created by mpaltanea on 02.08.2016.
 */
@Service
public class SlingContentRepositoryServiceImpl implements ContentRepositoryService {

    @Value("${apache.sling.url}")
    private String url;

    @Value("${apache.sling.user}")
    private String user;

    @Value("${apache.sling.pass}")
    private String pass;

    private HttpHeaders headers;

    @PostConstruct
    public void postConstruct() {
        headers = new HttpHeaders();
        String encoding = new String(Base64.encodeBase64((user + ":" + pass).getBytes()));
        headers.set("Authorization", "Basic " + encoding);
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
    }

    public void uploadResource(Resource resource, String repoNode, String repoFile) {
        RestTemplate template = new RestTemplate();

        MultiValueMap<String, Object> valueMap = new LinkedMultiValueMap<String, Object>();
        valueMap.add(repoFile, resource);

        template.exchange(
                url + "/" + repoNode,
                HttpMethod.POST,
                new HttpEntity<MultiValueMap<String, Object>>(valueMap, headers),
                String.class);
    }

    public String getResource(String resourcePath) {
        RestTemplate template = new RestTemplate();

        ResponseEntity<String> response = template.exchange(
                url + "/" + resourcePath,
                HttpMethod.GET,
                new HttpEntity<MultiValueMap<String, Object>>(new LinkedMultiValueMap<String, Object>(), headers),
                String.class);

        if (response!=null) {
            return response.getBody();
        }

        return null;
    }

    public void deleteResource(String resourcePath) {
        RestTemplate template = new RestTemplate();

        template.exchange(
            url + "/" + resourcePath,
            HttpMethod.DELETE,
            new HttpEntity<MultiValueMap<String, Object>>(new LinkedMultiValueMap<String, Object>(), headers),
            String.class);
    }

}
