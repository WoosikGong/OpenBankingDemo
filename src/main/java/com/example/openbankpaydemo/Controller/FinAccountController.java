package com.example.openbankpaydemo.Controller;

import com.example.openbankpaydemo.Entity.BnkTransaction;
import com.example.openbankpaydemo.Model.FinAccountIssuance;
import com.example.openbankpaydemo.Repository.TransactionRepository;
import com.example.openbankpaydemo.Service.HttpMethod;
import com.example.openbankpaydemo.Util.JacksonUtil;
import com.example.openbankpaydemo.Util.PropertiesUtil;
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
@RequestMapping("/fin")
public class FinAccountController {

    @Autowired
    TransactionRepository transactionRepository;

    @PostMapping(value = "/issueFinAccount")
    public ResponseEntity<String> issueFinAccount(@RequestParam String withdrawCheck,
                                  @RequestParam String birthday,
                                  @RequestParam String bankCode,
                                  @RequestParam String accountNum) throws IOException, InterruptedException {

        final String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 20);
        final String apiName = "OpenFinAccountDirect";

        FinAccountIssuance finAccountIssuance = new FinAccountIssuance(apiName, uuid);
        finAccountIssuance.setDrtrRgyn(withdrawCheck); // 출금이체 등록여부
        finAccountIssuance.setBrdtBrno(birthday); // 생일 또는 사업자번호
        finAccountIssuance.setBncd(bankCode); // 은행 코드 // 농협 : 011, 상호금융 : 012
        finAccountIssuance.setAcno(accountNum); // 계좌 번호

        BnkTransaction transaction = new BnkTransaction();
        transaction.setTrId(uuid);
        transaction.setSrcAccount(accountNum);
        transaction.setSrcBnkCode(bankCode);
        transaction.setTrType(apiName);

        String headerJson = JacksonUtil.serialization(finAccountIssuance);
        System.out.println("header : " + headerJson);

        HttpMethod httpMethod = new HttpMethod(PropertiesUtil.getProperty("url.nhApi") + apiName + ".nh");
        ResponseEntity<String> response = httpMethod.postRequest(headerJson);

        transactionRepository.save(transaction);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping(value = "/checkFinAccount")
    public ResponseEntity<String> checkFinAccount(@RequestParam String rgno,
                                                  @RequestParam String birthday) throws IOException, InterruptedException {


        final String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 20);
        final String apiName = "CheckOpenFinAccountDirect";

        FinAccountIssuance finAccountIssuance = new FinAccountIssuance(apiName, uuid);
        finAccountIssuance.setRgno(rgno);
        finAccountIssuance.setBrdtBrno(birthday);

        BnkTransaction transaction = new BnkTransaction();
        transaction.setTrId(uuid);

        String headerJson = JacksonUtil.serialization(finAccountIssuance);
        System.out.println("header : " + headerJson);

        HttpMethod httpMethod = new HttpMethod(PropertiesUtil.getProperty("url.nhApi") + apiName + ".nh");
        ResponseEntity<String> response = httpMethod.postRequest(headerJson);

        return new ResponseEntity(response, HttpStatus.OK);
    }

}
