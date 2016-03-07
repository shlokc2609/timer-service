package com.fquick.timer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

/**
 * Created by shlok.chaurasia on 07/03/16.
 */
public class BaseControllerTest {
    protected static RestTemplate restTemplate = new TestRestTemplate();
    public final static String baseUrl = "http://localhost:13800/";
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static Map<String, Object> post(String url, Map<String, Object> requestBody)
    {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        try {
            HttpEntity<String> httpEntity = new HttpEntity<String>(OBJECT_MAPPER.writeValueAsString(requestBody),
                    requestHeaders);
            Map<String, Object> apiResponse = restTemplate.postForObject(baseUrl+url,
                    httpEntity, Map.class,
                    Collections.EMPTY_MAP);
            return apiResponse;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
