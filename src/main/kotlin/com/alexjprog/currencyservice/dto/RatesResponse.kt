package com.alexjprog.currencyservice.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class RatesResponse(
    @JsonProperty("from")
    val inputCurrencyName: String,
    @JsonProperty("rates")
    val rates: Map<String, ConversionRate>
) {
    data class ConversionRate(
        @JsonProperty("to")
        val outputCurrencyName: String,
        @JsonProperty("rate")
        val conversionRate: Double
    )
}