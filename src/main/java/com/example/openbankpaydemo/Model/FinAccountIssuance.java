package com.example.openbankpaydemo.Model;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
public class FinAccountIssuance {

    CommonRequestHeader header;

    public FinAccountIssuance(String apiName, String tuno) throws IOException {
        header = new CommonRequestHeader(apiName, tuno);
    }

    // request
    private String DrtrRgyn;
    private String BrdtBrno;
    private String Bncd;
    private String Acno;

    // response
    private String Rgno;
    private String FinAcno;
    private String RgsnYmd;
}
// NH API가 PascalCase로 하기때문에 변수선언 Pascal 사용.
