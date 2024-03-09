package com.alexjprog.currencyservice.controller

import com.alexjprog.currencyservice.dto.ConversionResponse
import com.alexjprog.currencyservice.service.CurrencyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/convert")
class ConvertController @Autowired constructor(
    private val currencyService: CurrencyService
) {
    @GetMapping
    fun getConvertedCurrencies(
        @RequestParam("from_cur", defaultValue = "UAH") fromCurrencyName: String,
        @RequestParam("amount", required = true) amount: Double,
        @RequestParam("to_curs", required = false, defaultValue = "") toCurrencyNames: List<String>
    ): ConversionResponse {
        return ConversionResponse(
            inputCurrencyName = fromCurrencyName,
            inputCurrencyAmount = amount,
            conversions = currencyService.convertToCurrencies(fromCurrencyName, amount, toCurrencyNames)
                .associateBy { it.rateInfo.outputCurrencyName }
                .mapValues {
                    ConversionResponse.ConversionResult(
                        it.value.rateInfo.outputCurrencyName,
                        it.value.rateInfo.rate,
                        it.value.convertedAmount
                    )
                }
        )
    }
}