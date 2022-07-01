package com.example.openbankpaydemo.Controller;

import com.example.openbankpaydemo.Model.AccountTransfer;
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
@RequestMapping("/transfer")
public class TransferController {

    private static final Logger logger = LogManager.getLogger(HeaderController.class);

    @PostMapping(value = "/withdraw")
    public ResponseEntity<String> withdraw(@RequestParam String finAccount,
                                           @RequestParam String trAmount,
                                           @RequestParam(required = false) String drawDetatil,
                                           @RequestParam(required = false) String depositDetail) throws IOException, InterruptedException {

        final String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 20);
        final String apiName = "DrawingTransfer";
        AccountTransfer transfer = new AccountTransfer(apiName, uuid);

        transfer.setAcno(finAccount);
        transfer.setTram(trAmount);
        transfer.setDractOtlt(drawDetatil);
        transfer.setMractOtlt(depositDetail);

        String headerJson = JacksonUtil.serialization(transfer);
        logger.info("header : " + headerJson);

        HttpMethod httpMethod = new HttpMethod(PropertiesUtil.getProperty("url.nhApi") + apiName + ".nh");
        ResponseEntity<String> response = httpMethod.postRequest(headerJson);

        return new ResponseEntity("", HttpStatus.OK);
    }

    @PostMapping(value = "/bankDeposit")
    public ResponseEntity<String> bankDeposit(@RequestParam String bankCode,
                                              @RequestParam String account,
                                              @RequestParam String trAmount,
                                              @RequestParam(required = false) String drawDetatil,
                                              @RequestParam String depositDetail,
                                              @RequestParam Boolean isNHBank) throws IOException, InterruptedException {

        final String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 20);
        String apiName = null;

        if(isNHBank){
            if(!(bankCode.equals("011") || bankCode.equals("012"))){
                return new ResponseEntity("bankcode is not matching.", HttpStatus.BAD_REQUEST);
            }
            apiName = "ReceivedTransferAccountNumber";
        } // 농협은행
        else{
            if((bankCode.equals("011") || bankCode.equals("012"))){
                return new ResponseEntity("bankcode is not matching.", HttpStatus.BAD_REQUEST);
            }
            apiName = "ReceivedTransferOtherBank";
        } // 외부 은행

        AccountTransfer transfer = new AccountTransfer(apiName, uuid);

        transfer.setBncd(bankCode);
        transfer.setAcno(account);
        transfer.setTram(trAmount);
        transfer.setDractOtlt(drawDetatil);
        transfer.setMractOtlt(depositDetail);

        String headerJson = JacksonUtil.serialization(transfer);
        logger.info("header : " + headerJson);

        HttpMethod httpMethod = new HttpMethod(PropertiesUtil.getProperty("url.nhApi") + apiName + ".nh");
        ResponseEntity<String> response = httpMethod.postRequest(headerJson);

        return new ResponseEntity(response, HttpStatus.OK);
    }
}
