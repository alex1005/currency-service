package com.alexjprog.currencyservice.controller

import com.alexjprog.currencyservice.dto.ErrorResponse
import com.alexjprog.currencyservice.exceptions.WrongAmountException
import com.alexjprog.currencyservice.exceptions.WrongBaseCurrencyException
import com.alexjprog.currencyservice.exceptions.WrongCurrenciesException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException


@RestControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(WrongAmountException::class, WrongBaseCurrencyException::class, WrongCurrenciesException::class,
        MissingServletRequestParameterException::class, MethodArgumentTypeMismatchException::class)
    fun handleException(ex: Exception): ResponseEntity<ErrorResponse> {
        return ResponseEntity(ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.message.toString()), HttpStatus.BAD_REQUEST)
    }
}