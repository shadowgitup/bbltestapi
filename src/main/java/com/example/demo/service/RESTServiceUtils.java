package com.example.demo.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class RESTServiceUtils {

    private static final Logger logger = LogManager.getLogger(RESTServiceUtils.class);

    @Autowired
    public RestTemplateBuilder builder;

    public <T, R> R postForListObject(String apiUrl, String endpoint, T requestBody, Class<R> responseType) {

        RestTemplate rest = builder.build();

        try {

            String url = UriComponentsBuilder.fromUriString(apiUrl)
                    .path(endpoint)
                    .toUriString();

            return rest.postForObject(url, requestBody, responseType);
        } catch (Exception e) {
            logger.info(e.getMessage());
            return null;
        }
    }

    public <T, R> R postForObject(String apiUrl, String endpoint, T requestBody, Class<R> responseType) {

        RestTemplate rest = builder.build();

        try {

            String url = UriComponentsBuilder.fromUriString(apiUrl)
                    .path(endpoint)
                    .toUriString();

            return rest.postForObject(url, requestBody, responseType);
        } catch (Exception e) {
            logger.info(e.getMessage());
            return null;
        }
    }

    public <T, R> R getForObject(String apiUrl, String endpoint, Class<R> responseType) {
        RestTemplate rest = builder.build();

        try {

            String url = UriComponentsBuilder.fromUriString(apiUrl)
                    .path(endpoint)
                    .toUriString();

            return rest.getForObject(url, responseType);
        } catch (Exception e) {
            logger.info(e.getMessage());
            return null;
        }
    }

}
