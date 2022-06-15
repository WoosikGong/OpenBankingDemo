package com.example.openbankpaydemo.Model;

import com.example.openbankpaydemo.Util.isTunoCounter;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


// NH API가 PascalCase로 하기때문에 변수선언 Pascal 사용.

@Getter
@Setter
public class CommonRequestHeader {
    private String ApiNm;
    private String Tsymd;
    private String Trtm;
    static private String Iscd;
    private String FintechApsno = "001"; // 테스트 앱 고정
    private String ApiSvcCd = "DrawingTransferA"; // 테스트 앱 고정
    private String IsTuno; // 테스트 과정 변경 필요없음
    static private String AccessToken;

    public CommonRequestHeader() { }

    public CommonRequestHeader(String apiName, String tuno) throws IOException {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        this.ApiNm = apiName;
        this.Tsymd = now.format(formatter);

        formatter = DateTimeFormatter.ofPattern("HHmmss");
        this.Trtm = now.format(formatter);
        this.IsTuno = tuno;
    }
    public void setInfo(String iscd, String accessToken){
        this.Iscd = iscd;
        this.AccessToken = accessToken;
    }

    public String getIscd(){
        return this.Iscd;
    }

    public String getAccessToken(){
        return this.AccessToken;
    }
}
