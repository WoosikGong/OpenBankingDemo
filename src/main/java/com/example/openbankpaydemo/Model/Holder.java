package com.example.openbankpaydemo.Model;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@Setter
@Getter
public class Holder {

    CommonRequestHeader header;

    public Holder(String apiName, String tuno) throws IOException {
        header = new CommonRequestHeader(apiName, tuno);
    }

    //Request
    private String Bncd;
    private String Acno;

    //Response
    private String Dpmn;
}
