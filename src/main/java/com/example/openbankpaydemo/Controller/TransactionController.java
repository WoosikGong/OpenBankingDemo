package com.example.openbankpaydemo.Controller;

import com.example.openbankpaydemo.Model.TransactionHistory;
import com.example.openbankpaydemo.Service.HttpMethod;
import com.example.openbankpaydemo.Util.JacksonUtil;
import com.example.openbankpaydemo.Util.PropertiesUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private static final Logger logger = LogManager.getLogger(HeaderController.class);

    @PostMapping(value = "/history")
    public ResponseEntity<String> transactionHistory(@RequestParam String bankCode,
                                                     @RequestParam String accountNum,
                                                     @RequestParam String checkStart,
                                                     @RequestParam String checkEnd,
                                                     @RequestParam String requestCount,
                                                     @RequestParam(required = false, defaultValue = "A") String trCase,
                                                     @RequestParam(required = false, defaultValue = "ASC") String sortType,
                                                     @RequestParam(required = false, defaultValue = "1") String pageNum) throws IOException, InterruptedException {

        final String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 20);
        final String apiName = "InquireTransactionHistory";

        TransactionHistory history = new TransactionHistory(apiName, uuid);
        history.setBncd(bankCode);
        history.setAcno(accountNum);
        history.setInsymd(checkStart);
        history.setIneymd(checkEnd);
        history.setDmcnt(requestCount);
        history.setTrnsDsnc(trCase);
        history.setLnsq(sortType);
        history.setPageNo(pageNum);


        String headerJson = JacksonUtil.serialization(history);
        logger.info("header : " + headerJson);

        HttpMethod httpMethod = new HttpMethod(PropertiesUtil.getProperty("url.nhApi") + apiName + ".nh");
        ResponseEntity<String> response = httpMethod.postRequest(headerJson);

        return new ResponseEntity(response, HttpStatus.OK);
    }

}
