package com.example.openbankpaydemo.Model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommonResponseHeader {
    private String ApiNm;
    private String Tsymd;
    private String Trtm;
    private String Iscd;
    private String FintechApsno;
    private String APISvcCd;
    private String Istuno;
    private String Rpcd;
    private String Rsms;

}
// NH API가 PascalCase로 하기때문에 변수선언 Pascal 사용.
