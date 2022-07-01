package com.example.openbankpaydemo.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "TestTable")
public class TestTable {
    @Id

    @Column(name = "TestColumn")
    private String testColumn;

    @Column(name = "TestColumn2")
    private String testColumn2;
}
