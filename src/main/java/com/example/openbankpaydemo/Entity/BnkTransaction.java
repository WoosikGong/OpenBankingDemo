package com.example.openbankpaydemo.Entity;


import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "bnkTransaction")
public class BnkTransaction {
    @Id

    String trId;
    String srcAccount;
    String dstAccount;
    String finAccount;
    String srcBnkCode;
    String dstBnkCode;
    String trType;
    int amount;
}
