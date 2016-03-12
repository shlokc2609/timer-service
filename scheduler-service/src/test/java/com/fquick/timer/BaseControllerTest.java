package com.fquick.timer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fquick.timer.domain.enums.SubscriptionType;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void createClient(String externalId, String useCase,
                             SubscriptionType subscriptionType, String url, String exchangeName)
    {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("client_use_case", useCase);
        requestBody.put("subscription_type", subscriptionType);
        requestBody.put("client_external_id", externalId);
        requestBody.put("url", url);
        requestBody.put("is_active", true);
        requestBody.put("exchange_name", exchangeName);
        requestBody.put("client_use_case_description", "test Description");
        requestBody.put("subscription_description", "test Description");
        post("client/register", requestBody);
    }
}
