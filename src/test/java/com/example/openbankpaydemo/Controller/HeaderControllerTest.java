package com.example.openbankpaydemo.Controller;

import com.example.openbankpaydemo.Util.PropertiesUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HeaderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static String iscd;
    private static String accessToken;
    private static String testResult;


    @BeforeAll
    public static void set(){
        iscd = PropertiesUtil.getProperty("api.iscd");
        accessToken = PropertiesUtil.getProperty("api.accessToken");
        testResult = "[{\"testColumn\":\"test\",\"testColumn2\":\"test\"},{\"testColumn\":\"test2\",\"testColumn2\":\"test2\"}]";
    }

    @Test
    @DisplayName("Header Set Succeed")
    public void setHeaderTest_Succeed() throws Exception{
        mockMvc.perform(post("/setHeader")
                        .param("iscd", this.iscd)
                        .param("accessToken", this.accessToken)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Header Set Failed_ParamsNotMatched")
    public void setHeaderTestParamNotMatched_Failed() throws Exception{
        mockMvc.perform(post("/setHeader")
                        .param("accessToken", this.accessToken)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isBadRequest());


        mockMvc.perform(post("/setHeader")
                        .param("iscd", this.iscd)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Header Set Failed_ParamLength")
    public void setHeaderTestParamLength_Failed() throws Exception{
        String iscdMock = "";
        String atMock = "";

        mockMvc.perform(post("/setHeader")
                        .param("iscd",iscdMock)
                        .param("accessToken", this.accessToken)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isBadRequest());
        mockMvc.perform(post("/setHeader")
                        .param("iscd", this.iscd)
                        .param("accessToken", atMock)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Header Get")
    public void getHeaderTest() throws Exception{
        String result = "iscd : " + this.iscd + ", access : " + this.accessToken;

        mockMvc.perform(get("/getHeaderTest"))
                .andExpect(status().isOk())
                .andExpect(content().string(result));
    }

    @Test
    @DisplayName("DB Check")
    public void selectTest() throws Exception{
        mockMvc.perform(get("/select"))
                .andExpect(status().isOk())
                .andExpect(content().string(testResult));
    }
}
