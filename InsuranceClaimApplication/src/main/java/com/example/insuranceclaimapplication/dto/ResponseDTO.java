package com.example.insuranceclaimapplication.dto;

import lombok.Data;

@Data
public class ResponseDTO {
    String message;
    Object data;
    public ResponseDTO(String message, Object data) {
        this.message = message;
        this.data = data;
    }
}
