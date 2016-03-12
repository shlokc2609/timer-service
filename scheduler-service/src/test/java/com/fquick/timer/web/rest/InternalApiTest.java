package com.fquick.timer.web.rest;

import com.flipkart.restbus.client.core.MessageSender;
import com.flipkart.restbus.client.entity.Event;
import com.flipkart.restbus.client.repository.OutboundMessageRepository;
import com.fquick.Application;
import com.fquick.timer.TestUtil;
import com.fquick.timer.domain.enums.JobStatus;
import com.fquick.timer.domain.model.Client;
import com.fquick.timer.domain.model.Job;
import com.fquick.timer.repository.ClientRepository;
import com.fquick.timer.repository.JobRepository;
import com.fquick.timer.service.JobSchedulerService;
import com.fquick.timer.service.factory.VaradhiMessageSenderFactory;
import com.fquick.timer.service.impl.JobSchedulerServiceImpl;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.View;

import java.util.List;

/**
 * Created by shlok.chaurasia on 12/03/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
@ActiveProfiles("test")
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeTestRun.sql"),
})

public class InternalApiTest extends AbstractControllerTest {

    @Autowired
    JobRepository jobRepository;

    @Autowired
    ClientRepository clientRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        super.setUp();
    }


    @Test
    public void testJobExecution() throws Exception {

        TestUtil.createClient(defaultClientId, defaultTestUseCase, defaultSubscriptionType, defaultTestUrl, defaultExchangeName);
        TestUtil.scheduleJob(defaultClientId, payload.toString(), defaultTestUseCase);
        TestUtil.scheduleJob(defaultClientId, payload.toString(), defaultTestUseCase);
        TestUtil.executeJob(defaultClientId);
    }
}
