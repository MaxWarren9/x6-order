package com.sds.x6_order.controller;

import com.sds.x6_order.exception.BadRequestException;
import com.sds.x6_order.exception.OrderException;
import com.sds.x6_order.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> handleBadRequest(BadRequestException e) {
        return ResponseEntity
                .badRequest()
                .body(new ApiError(false, e.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(NotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiError(false, e.getMessage()));
    }

    @ExceptionHandler(OrderException.class)
    public ResponseEntity<ApiError> handleOrderError(OrderException e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ApiError(false, e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleOther(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiError(false, "Internal error"));
    }
}
