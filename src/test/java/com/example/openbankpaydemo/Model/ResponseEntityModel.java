package com.example.openbankpaydemo.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseEntityModel {
    private Object headers;
    private String body;
    private String statusCode;
    private int statusCodeValue;
}
