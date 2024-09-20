package org.example.filedb.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

public record ExceptionResponse(
    LocalDateTime timeStamp,
    Integer status,
    String error,
    String message,
    String path,
    @JsonInclude(JsonInclude.Include.NON_NULL) Map<String, Object> validationErrors
    ) implements Serializable {}
