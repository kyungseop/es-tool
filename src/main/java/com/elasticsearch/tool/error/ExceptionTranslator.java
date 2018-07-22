package com.elasticsearch.tool.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ExceptionTranslator {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorVM processException(Exception exception) {
        log.warn("processException : {}", exception);
        return new ErrorVM(exception.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorVM processIllegalArgumentException(IllegalArgumentException exception) {
        log.warn("processIllegalArgumentException : {}", exception);

        String description = "";

        switch (exception.getMessage()) {
            case "error.qdsl.malformed.url":
                description = "Malformed query url. ex) GET lcoalshot:9200/index";
                break;
            default:
                break;
        }

        return new ErrorVM(exception.getMessage(), description);
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorVM processNullPointException(NullPointerException exception) {
        log.warn("processNullPointException : {}", exception);
        return new ErrorVM(exception.getMessage());
    }
}
