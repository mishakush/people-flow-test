package com.example.peopleflowdemo.employeemanagement.exception;


import com.example.peopleflowdemo.employeemanagement.dto.CommonResponse;
import lombok.Getter;


@Getter
public class BaseRestResponseException extends RuntimeException {

    private int httpStatus;

    private CommonResponse responseBody;

    public BaseRestResponseException(String exceptionMessage, CommonResponse responseBody) {
        super(exceptionMessage);
        this.httpStatus = 400;
        this.responseBody = responseBody;
    }


    public BaseRestResponseException(String exceptionMessage, String code, String devMessage, String userMessage) {
        super(exceptionMessage);
        this.httpStatus = 400;
        this.responseBody = CommonResponse.builder()
                .code(code)
                .devMessage(devMessage)
                .userMessage(userMessage)
                .build();
    }


    public BaseRestResponseException(int httpStatus, String exceptionMessage, String code, String devMessage, String userMessage) {
        super(exceptionMessage);
        this.httpStatus = httpStatus;
        this.responseBody = CommonResponse.builder()
                .code(code)
                .devMessage(devMessage)
                .userMessage(userMessage)
                .build();
    }
}
