package com.alexjprog.currencyservice.service.model

data class RateModel(
    val inputCurrencyName: String,
    val outputCurrencyName: String,
    val rate: Double
)
