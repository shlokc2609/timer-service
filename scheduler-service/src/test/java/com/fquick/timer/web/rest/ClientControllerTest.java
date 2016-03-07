package com.fquick.timer.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fquick.Application;
import com.fquick.timer.BaseControllerTest;
import com.fquick.timer.domain.enums.SubscriptionType;
import com.fquick.timer.repository.ClientRepository;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shlok.chaurasia on 07/03/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
@ActiveProfiles("test")
public class ClientControllerTest extends BaseControllerTest{

    @Autowired
    private ClientRepository clientRepository;



    @Test
    public void testCreateClient() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("clientUseCase", "Orchastration");
        requestBody.put("subscriptionType", SubscriptionType.ASYNC_TOPIC);
        requestBody.put("clientExternalId", "ASSD");
        requestBody.put("isActive", true);
        requestBody.put("clientUseCaseDescription", "test Description");
        requestBody.put("subscriptionDescription", "test Description");
        Map<String, Object> apiResponse = post("client/register", requestBody);
        Assert.assertNotNull(apiResponse);
    }
}
