package com.elasticsearch.tool.error;

import lombok.Data;

@Data
public class ErrorVM {

    private final String message;
    private final String description;

    public ErrorVM(String message) {
        this(message, null);
    }

    public ErrorVM(String message, String description) {
        this.message = message;
        this.description = description;
    }

}
