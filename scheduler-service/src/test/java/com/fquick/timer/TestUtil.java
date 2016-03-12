package com.fquick.timer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fquick.timer.domain.enums.SubscriptionType;
import com.fquick.timer.dto.RegisterClientDto;
import com.fquick.timer.dto.RegisterSchedulerDto;
import org.joda.time.DateTime;
import org.json.JSONObject;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.StatusResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shlok.chaurasia on 12/03/16.
 */
public class TestUtil {
    public static MockMvc mockMvc;
    public static Logger logger;
    @Transactional
    public static void createClient(String externalId, String useCase,
                                    SubscriptionType subscriptionType, String url, String exchangeName) throws Exception
    {
        RegisterClientDto registerClientDto = new RegisterClientDto();
        registerClientDto.setClientExternalId(externalId);
        registerClientDto.setClientUseCase(useCase);
        registerClientDto.setSubscriptionType(subscriptionType);
        registerClientDto.setUrl(url);
        registerClientDto.setExchange_name(exchangeName);
        registerClientDto.setMethod("POST");
        JSONObject header = new JSONObject();
        header.put("X_CLIENT_ID", "TestClient");
        registerClientDto.setHeader(header.toString());
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/client/register").
                content(convertObjectToJsonBytes(registerClientDto)).
                contentType(MediaType.APPLICATION_JSON).
                accept(MediaType.APPLICATION_JSON));
        logger.info("Create Client Response: " + response.andReturn().getResponse().getContentAsString());
        Assert.assertEquals(HttpStatus.CREATED.value(), response.andReturn().getResponse().getStatus());
    }
    @Transactional
    public static void scheduleJob(String externalId, String payload, String useCase) throws Exception
    {
        Map<String, String> registerSchedulerDto = new HashMap<>();
        registerSchedulerDto.put("client_external_id",externalId);
        registerSchedulerDto.put("payload",payload);
        registerSchedulerDto.put("use_case",useCase);
        registerSchedulerDto.put("scheduled_time",(new DateTime()).toString());
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/job/schedule").
                content(convertObjectToJsonBytes(registerSchedulerDto)).
                contentType(MediaType.APPLICATION_JSON).
                accept(MediaType.APPLICATION_JSON));
        logger.info("Job Scheduled Response: "+ response.andReturn().getResponse().getContentAsString());
        Assert.assertEquals(HttpStatus.CREATED.value(), response.andReturn().getResponse().getStatus());
    }

    @Transactional
    public static void executeJob(String clientId) throws Exception
    {
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/internal/"+clientId+"/execute").
                contentType(MediaType.APPLICATION_JSON).
                accept(MediaType.APPLICATION_JSON));
        logger.info("Job Execute Response: "+ response.andReturn().getResponse().getContentAsString());
        Assert.assertEquals(HttpStatus.NO_CONTENT.value(), response.andReturn().getResponse().getStatus());
    }

    public static String convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

}
