package com.example.openbankpaydemo.Model;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@Setter
@Getter
public class Balance {

    CommonRequestHeader header;

    public Balance(String apiName, String tuno) throws IOException {
        header = new CommonRequestHeader(apiName, tuno);
    }

    // request, response
    private String FinAcno;

    // response
    private String AthrCnfrTckt;
    private String WebUrl;
    private String AndInltUrl;
    private String AndAppUrl;
    private String AndWebUrl;
    private String IosAppUrl;
    private String IosWebUrl;
    private String Ldbl;
    private String RlpmAbamt;
}
// NH API가 PascalCase로 하기때문에 변수선언 Pascal 사용.
