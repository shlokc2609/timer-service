package com.fquick.timer.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fquick.Application;
import com.fquick.timer.BaseControllerTest;
import com.fquick.timer.TestUtil;
import com.fquick.timer.domain.enums.SubscriptionType;
import com.fquick.timer.domain.model.Client;
import com.fquick.timer.domain.model.ClientRegistrationDetail;
import com.fquick.timer.domain.model.UseCase;
import com.fquick.timer.repository.ClientDetailRepository;
import com.fquick.timer.repository.ClientRepository;
import com.fquick.timer.repository.UseCaseRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shlok.chaurasia on 07/03/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
@ActiveProfiles("test")
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeTestRun.sql"),
})
public class ClientRegistrationControllerTest extends AbstractControllerTest{

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private UseCaseRepository useCaseRepository;

    @Autowired
    private ClientDetailRepository clientDetailRepository;

    @Before
    public void setUp(){
        super.setUp();
    }

    @Test
    public void testRegisterClient() throws Exception{

        TestUtil.createClient(defaultClientId,defaultTestUseCase,defaultSubscriptionType,
                defaultTestUrl,defaultExchangeName);
        List<ClientRegistrationDetail> clientDetails = clientDetailRepository.findByClientExternalIdAndUseCase(defaultClientId,
                defaultTestUseCase);
        Assert.assertEquals(1, clientDetails.size());
        ClientRegistrationDetail clientRegistrationDetail = clientDetails.get(0);
        Assert.assertEquals(defaultSubscriptionType, clientRegistrationDetail.getSubscriptionType());
        Assert.assertEquals(defaultTestUrl, clientRegistrationDetail.getUseCase().getUrl());
        Assert.assertEquals(defaultExchangeName, clientRegistrationDetail.getUseCase().getExchangeName());
    }

    @Test
    public void testUseCaseCreation() throws Exception{
        String clientId2 = defaultClientId + "2";
        TestUtil.createClient(defaultClientId,defaultTestUseCase,defaultSubscriptionType,
                defaultTestUrl,defaultExchangeName);
        TestUtil.createClient(clientId2,defaultTestUseCase,defaultSubscriptionType,
                defaultTestUrl,defaultExchangeName);
        List<ClientRegistrationDetail> clientDetails1 = clientDetailRepository.findByClientExternalIdAndUseCase(defaultClientId,
                defaultTestUseCase);
        List<ClientRegistrationDetail> clientDetails2 = clientDetailRepository.findByClientExternalIdAndUseCase(clientId2,
                defaultTestUseCase);
        Assert.assertEquals(1, clientDetails1.size());
        Assert.assertEquals(1, clientDetails2.size());
        List<UseCase> useCases = useCaseRepository.findAllByName(defaultTestUseCase);
        Assert.assertEquals(1, useCases.size());
    }
}
