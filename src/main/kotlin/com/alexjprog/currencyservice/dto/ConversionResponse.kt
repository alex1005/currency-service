package com.alexjprog.currencyservice.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ConversionResponse(
    @JsonProperty("from")
    val inputCurrencyName: String,
    @JsonProperty("amount")
    val inputCurrencyAmount: Double,
    @JsonProperty("conversions")
    val conversions: Map<String, ConversionResult>
) {
    data class ConversionResult(
        @JsonProperty("to")
        val outputCurrencyName: String,
        @JsonProperty("rate")
        val conversionRate: Double,
        @JsonProperty("converted_amount")
        val outputCurrencyAmount: Double,
    )
}