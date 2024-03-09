package com.alexjprog.currencyservice.dto

data class CurrencyResponse(
    val name: String,
    val longName: String? = null
)