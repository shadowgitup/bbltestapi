package com.example.demo.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class RESTServiceUtils {

    private static final Logger logger = LogManager.getLogger(RESTServiceUtils.class);

    @Autowired
    public RestTemplateBuilder builder;

    public <T, R> List<R> postForListObject(String apiUrl, String endpoint, T requestBody, Class<R> responseType) {

        RestTemplate rest = builder.build();

        try {
            String url = UriComponentsBuilder.fromUriString(apiUrl)
                    .path(endpoint)
                    .toUriString();
            ParameterizedTypeReference<List<R>> responseTypeReference = new ParameterizedTypeReference<List<R>>() {
            };

            ResponseEntity<List<R>> responseEntity = rest.exchange(
                    url,
                    HttpMethod.GET,
                    new HttpEntity<>(requestBody),
                    responseTypeReference);

            return responseEntity.getBody();

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

    public <T, R> R updateForObject(String apiUrl, String endpoint, T requestBody, Class<R> responseType) {

        RestTemplate rest = builder.build();

        try {
            String url = UriComponentsBuilder.fromUriString(apiUrl)
                    .path(endpoint)
                    .toUriString();

            ResponseEntity<R> responseEntity = rest.exchange(
                    url,
                    HttpMethod.PUT,
                    new HttpEntity<>(requestBody),
                    responseType);

            return responseEntity.getBody();
        } catch (Exception e) {
            logger.info(e.getMessage());
            return null;
        }
    }

    public <T> void deleteForObject(String apiUrl, String endpoint, Long id) {
        RestTemplate rest = builder.build();

        try {
            String url = UriComponentsBuilder.fromUriString(apiUrl)
                    .path(endpoint)
                    .toUriString();

            rest.delete(url, id);

        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }
}
