package com.example.openbankpaydemo.Controller;


import com.example.openbankpaydemo.Model.CommonRequestHeader;
import com.example.openbankpaydemo.Util.JacksonUtil;
import com.example.openbankpaydemo.Util.PropertiesUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(locations = "")
@ActiveProfiles("dev")
public class AccountCheckControllerTest {

    @Autowired
    private MockMvc mockMvc;

    Logger logger = LogManager.getLogger(AccountCheckController.class);

    private static String finAccount;
    private static String iscd;
    private static String accessToken;


    @Autowired
    static ApplicationContext context;


    @BeforeAll
    public static void set(){
        Environment environment = context.getEnvironment();

        finAccount = PropertiesUtil.getProperty("api.finAccount");
        iscd = PropertiesUtil.getProperty("api.iscd");
        accessToken = PropertiesUtil.getProperty("api.accessToken");
        CommonRequestHeader commonRequestHeader = new CommonRequestHeader();
        commonRequestHeader.setInfo(iscd, accessToken);
    }

    @Test
    @DisplayName("Check Balance Succeed")
    public void checkAccountBalanceTest() throws Exception{
        MvcResult result = mockMvc.perform(post("/account/checkBalance")
                        .param("finAccount", finAccount)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andReturn();

        Map<String, Object> response = JacksonUtil.deserialization(result.getResponse().getContentAsString(), Map.class);
        Map<String, Object> body = JacksonUtil.deserialization((String)response.get("body"), Map.class);

        assertFalse(body.isEmpty(), "Response Body is null");
        assertNotNull(body.get("FinAcno"), "FinAcno is not matched.");
        assertNotNull(body.get("Ldbl"), "Header error.");
        assertNotNull(body.get("RlpmAbamt"), "Header error.");
    }

}
