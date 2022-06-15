package com.example.openbankpaydemo.Model;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@Setter
@Getter
public class AccountTransfer {

    CommonRequestHeader header;

    public AccountTransfer(String apiName, String tuno) throws IOException {
        header = new CommonRequestHeader(apiName, tuno);
    }

    private String Bncd;
    private String Acno;
    private String FinAcno;
    private String Tram;
    private String DractOtlt;
    private String MractOtlt;
}
