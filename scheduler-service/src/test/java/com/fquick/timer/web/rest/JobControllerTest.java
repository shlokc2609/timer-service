package com.fquick.timer.web.rest;

import com.fquick.Application;
import com.fquick.timer.TestUtil;
import com.fquick.timer.domain.model.Client;
import com.fquick.timer.domain.model.Job;
import com.fquick.timer.repository.ClientRepository;
import com.fquick.timer.repository.JobRepository;
import com.fquick.timer.repository.UseCaseRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by shlok.chaurasia on 12/03/16.
 */
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeTestRun.sql"),
})
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
@ActiveProfiles("test")
@Transactional
public class JobControllerTest extends AbstractControllerTest {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UseCaseRepository useCaseRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void testJobSchedule() throws Exception {
        TestUtil.createClient(defaultClientId,defaultTestUseCase,defaultSubscriptionType,
                defaultTestUrl,defaultExchangeName);
        logger.info(useCaseRepository.findByName(defaultTestUseCase).toString());
        TestUtil.scheduleJob(defaultClientId, payload.toString(), defaultTestUseCase);
        Client client = clientRepository.findByExternalId(defaultClientId);
        List<Job> jobs= jobRepository.findByClientId(client.getId());
        Assert.assertEquals(1, jobs.size());
    }

}
