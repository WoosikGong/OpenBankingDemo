package com.example.openbankpaydemo.Controller;

import com.example.openbankpaydemo.Entity.TestTable;
import com.example.openbankpaydemo.Model.CommonRequestHeader;
import com.example.openbankpaydemo.Repository.TestRepository;
import com.example.openbankpaydemo.Util.JacksonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HeaderController {

    private static final Logger logger = LogManager.getLogger(HeaderController.class);

    @Autowired
    TestRepository testRepository;

    @PostMapping(value = "/setHeader")
    public ResponseEntity setRequestHeader(@RequestParam String iscd, @RequestParam String accessToken) throws JsonProcessingException {
        CommonRequestHeader commonRequestHeader = new CommonRequestHeader();

        if(iscd.length() != 6 || accessToken.length() != 64) {
            logger.info("iscd or accessToken is invalid.");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        commonRequestHeader.setInfo(iscd, accessToken);
        logger.info(JacksonUtil.serialization(commonRequestHeader));
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/getHeaderTest")
    public ResponseEntity<String> getHeaderTest() throws JsonProcessingException {
        Map<String, String> map = new HashMap<>();
        CommonRequestHeader commonRequestHeader = new CommonRequestHeader();
        map.put("iscd", commonRequestHeader.getIscd());
        map.put("access",commonRequestHeader.getAccessToken() );
        logger.info(new ObjectMapper().writeValueAsString(map));
        return new ResponseEntity("iscd : " + commonRequestHeader.getIscd() + ", access : " + commonRequestHeader.getAccessToken(), HttpStatus.OK);
    }

    @RequestMapping("/select")
    public List<TestTable> testSelect(Model model) {
        List<TestTable> testList = testRepository.findAll(Sort.by(Sort.Direction.ASC, "testColumn"));
        return testList;
    }
}
