package com.fquick.timer.web.rest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fquick.timer.TestUtil;
import com.fquick.timer.domain.enums.SubscriptionType;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shlok.chaurasia on 12/03/16.
 */
public abstract class AbstractControllerTest {
    public static final String defaultClientId = "TestClient";
    public static final String defaultTestUseCase = "TestUseCase";
    public static final String defaultTestUrl = "http://localhost:13800/";
    public static final String defaultExchangeName = "localhost.fquick.shipment.status";
    public static final SubscriptionType defaultSubscriptionType = SubscriptionType.ASYNC_QUEUE;
    public static final JSONObject payload = new JSONObject();
    static{
        try {
            payload.put("testAttribute", "testAttributeValue");
        }
        catch(Exception e)
        {

        }
    }
    protected MockMvc mvc;
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected WebApplicationContext webApplicationContext;

    /**
     * Prepares the test class for execution of web tests. Builds a MockMvc
     * instance. Call this method from the concrete JUnit test class in the
     * <code>@Before</code> setup method.
     */
    protected void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        TestUtil.mockMvc=mvc;
        TestUtil.logger=logger;
    }

    /**
     * Prepares the test class for execution of web tests. Builds a MockMvc
     * instance using standalone configuration facilitating the injection of
     * Mockito resources into the controller class.
     *
     * @param controller A controller object to be tested.
     */
   /* protected void setUp(BaseController controller) {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    } */

    /**
     * Maps an Object into a JSON String. Uses a Jackson ObjectMapper.
     *
     * @param obj The Object to map.
     * @return A String of JSON.
     * @throws JsonProcessingException Thrown if an error occurs while mapping.
     */
    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

    /**
     * Maps a String of JSON into an instance of a Class of type T. Uses a
     * Jackson ObjectMapper.
     *
     * @param json  A String of JSON.
     * @param clazz A Class of type T. The mapper will attempt to convert the
     *              JSON into an Object of this Class type.
     * @return An Object of type T.
     * @throws JsonParseException   Thrown if an error occurs while mapping.
     * @throws JsonMappingException Thrown if an error occurs while mapping.
     * @throws IOException          Thrown if an error occurs while mapping.
     */
    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, clazz);
    }

}
