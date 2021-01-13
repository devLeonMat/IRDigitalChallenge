package com.rleon.msclients.model.dto;

import lombok.Data;

@Data
public class ResponseApi {

    private int code;
    private String message;
    private Object data;


    public ResponseApi(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


    public ResponseApi(int cod, String message) {
        this.code = cod;
        this.message = message;
    }
}
