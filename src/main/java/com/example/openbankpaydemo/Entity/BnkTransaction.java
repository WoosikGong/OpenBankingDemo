package com.example.openbankpaydemo.Entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@Table(name = "Transaction")
public class BnkTransaction {
    @Id
    @GeneratedValue

    String trId;
    String srcAccount;
    String dstAccount;
    String finAccount;
    String srcBnkCode;
    String dstBnkCode;
    String trType;
    int amount;
}
