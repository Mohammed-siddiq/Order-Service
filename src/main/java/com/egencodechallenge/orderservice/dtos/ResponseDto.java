package com.egencodechallenge.orderservice.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {

    private String message;
    private String id;

    public ResponseDto(String message) {
        this.message = message;
    }


}
