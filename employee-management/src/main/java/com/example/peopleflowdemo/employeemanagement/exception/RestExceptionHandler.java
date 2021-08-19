package com.example.peopleflowdemo.employeemanagement.exception;

import com.example.peopleflowdemo.employeemanagement.dto.CommonResponse;
import com.example.peopleflowdemo.employeemanagement.dto.ConstraintResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static org.springframework.http.ResponseEntity.unprocessableEntity;


@RequiredArgsConstructor
@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.warn(ex.toString());

        BindingResult bindingResult = ex.getBindingResult();

        if (bindingResult.hasGlobalErrors()) {
            return unprocessableEntity().body(
                    CommonResponse.builder()
                            .code(applicationCode + "_422")
                            .devMessage("Class level validation: " + bindingResult.getTarget().getClass().getName())
                            .userMessage(bindingResult.getGlobalErrors().get(0).getDefaultMessage()).build()
            );
        }

        Map<String, String> errorsMap = new HashMap<>();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        fieldErrors.forEach(fieldError -> errorsMap.put(fieldError.getField(), fieldError.getDefaultMessage()));

        return unprocessableEntity().body(new ConstraintResponse(errorsMap));
    }


    @ExceptionHandler(value = {BaseRestResponseException.class})
    @ResponseBody
    public ResponseEntity<CommonResponse> handleBaseRestResponse(BaseRestResponseException e) {
        log.warn(e.getMessage());
        return ResponseEntity.status(e.getHttpStatus()).body(e.getResponseBody());
    }


    @ExceptionHandler(value = {HttpMediaTypeNotSupportedException.class})
    @ResponseBody
    public ResponseEntity<CommonResponse> handleBaseRestResponse(HttpMediaTypeNotSupportedException e) {
        log.warn(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(
                CommonResponse.builder()
                        .devMessage(e.toString())
                        .build()
        );
    }


    @ExceptionHandler(value = {Throwable.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public CommonResponse handleThrowable(Throwable e, Locale locale) {
        log.error(e.getMessage(), e);
        return CommonResponse.builder()
                .code(applicationCode + "_500")
                .devMessage(e.toString())
                .userMessage(messageSource.getMessage(applicationCode + "_500", null, locale))
                .build();
    }


    private final MessageSource messageSource;

    @Value("${application.code:SYS}")
    private String applicationCode;


}

