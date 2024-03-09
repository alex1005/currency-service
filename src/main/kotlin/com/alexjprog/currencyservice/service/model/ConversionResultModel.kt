package com.alexjprog.currencyservice.service.model

data class ConversionResultModel(
    val rateInfo: RateModel,
    val toConvertAmount: Double,
    val convertedAmount: Double
)
