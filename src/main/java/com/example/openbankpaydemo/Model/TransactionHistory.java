package com.example.openbankpaydemo.Model;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.List;

@Setter
@Getter
public class TransactionHistory {

    CommonRequestHeader header;

    public TransactionHistory(String apiName, String tuno) throws IOException {
        header = new CommonRequestHeader(apiName, tuno);
    }

    // Request
    private String Bncd;
    private String Acno;
    private String Insymd;
    private String Ineymd;
    private String TrnsDsnc;
    private String Lnsq;
    private String PageNo;
    private String Dmcnt;


    // Response
    private List<TransactionHistory> REC;
    
    private String CtntDataYn;
    private String TotCnt;
    private String Iqtcnt;
    private String Trdd;
    private String Txtm;
    private String MnrcDrotDsnc;
    private String Tram;
    private String AftrBlnc;
    private String TrnsAfAcntBlncSmblCd;
    private String Smr;
    private String HnisCd;
    private String HnbrCd;
    private String Ccyn;
    private String Tuno;
    private String BnprCntn;
}
