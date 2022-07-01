package com.example.openbankpaydemo.Controller;

import com.example.openbankpaydemo.Entity.BnkTransaction;
import com.example.openbankpaydemo.Model.Balance;
import com.example.openbankpaydemo.Model.Holder;
import com.example.openbankpaydemo.Repository.TransactionRepository;
import com.example.openbankpaydemo.Service.HttpMethod;
import com.example.openbankpaydemo.Util.JacksonUtil;
import com.example.openbankpaydemo.Util.PropertiesUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/account")
public class AccountCheckController {

    Logger logger = LogManager.getLogger(AccountCheckController.class);
    
    @Autowired
    TransactionRepository transactionRepository;

    @PostMapping(value = "/checkBalance")
    public ResponseEntity<String> checkAccountBalance(@RequestParam String finAccount) throws IOException, InterruptedException {
        final String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 20); // IsTuno 20글자 제한
        final String apiName = "InquireBalance";
        Balance balance = new Balance(apiName, uuid);
        balance.setFinAcno(finAccount);

        BnkTransaction transaction = new BnkTransaction();
        transaction.setTrId(uuid);
        transaction.setFinAccount(finAccount);
        transaction.setTrType(apiName);

        String headerJson = JacksonUtil.serialization(balance);
        logger.info("header : " + headerJson);

        HttpMethod httpMethod = new HttpMethod(PropertiesUtil.getProperty("url.nhApi") + apiName + ".nh");
        ResponseEntity<String> response = httpMethod.postRequest(headerJson);

        transactionRepository.saveAndFlush(transaction);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping(value = "/checkHolder")
    public ResponseEntity<String> checkAccountHolder(@RequestParam String bankCode,
                                                     @RequestParam String accountNum,
                                                     @RequestParam(required = false, defaultValue = "true") boolean isNHBank) throws IOException, InterruptedException {


        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 20);
        StringBuffer sb = new StringBuffer();

        logger.info(isNHBank);

        if(isNHBank){
            if(!(bankCode.equals("011") || bankCode.equals("012"))){
                return new ResponseEntity("bankcode is not matching.", HttpStatus.BAD_REQUEST);
            }
            sb.append("InquireDepositorAccountNumber");
        } // 농협은행
        else{
            if((bankCode.equals("011") || bankCode.equals("012"))){
                return new ResponseEntity("bankcode is not matching.", HttpStatus.BAD_REQUEST);
            }
            sb.append("InquireDepositorOtherBank");
        } // 외부 은행

        Holder holder = new Holder(sb.toString(), uuid);
        holder.setBncd(bankCode);
        holder.setAcno(accountNum);

        BnkTransaction transaction = new BnkTransaction();
        transaction.setTrId(uuid);
        transaction.setDstBnkCode(bankCode);
        transaction.setDstAccount(accountNum);
        transaction.setTrType(sb.toString());

        String headerJson = JacksonUtil.serialization(holder);
        logger.info("header : " + headerJson);

        HttpMethod httpMethod = new HttpMethod(PropertiesUtil.getProperty("url.nhApi") + sb + ".nh");
        ResponseEntity<String> response = httpMethod.postRequest(headerJson);

        transactionRepository.saveAndFlush(transaction);

        return new ResponseEntity(response, HttpStatus.OK);
    }
}
