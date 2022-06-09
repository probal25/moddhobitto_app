package com.probal.moddhobitto.core.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.validation.BindingResult;

import java.util.Objects;

@Data
@Builder
public class ErrorResponse {

    private int errorCode;

    private String fieldName;

    private String errorMessage;

    public static ErrorResponse create(BindingResult bindingResult, int errorCode) {
        return ErrorResponse.builder()
                .fieldName(Objects.requireNonNull(bindingResult.getFieldError()).getField())
                .errorMessage(bindingResult.getFieldError().getDefaultMessage())
                .errorCode(errorCode)
                .build();
    }

}
