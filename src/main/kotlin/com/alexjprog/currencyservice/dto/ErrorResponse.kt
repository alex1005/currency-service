package com.alexjprog.currencyservice.dto

data class ErrorResponse(
    val statusCode: Int,
    val message: String?
)