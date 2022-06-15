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
@Table(name = "TestTable")
public class TestTable {
    @Id
    @GeneratedValue

    private String TestColumn;
    private String TestColumn2;
}
