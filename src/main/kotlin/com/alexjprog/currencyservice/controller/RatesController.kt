package com.alexjprog.currencyservice.controller

import com.alexjprog.currencyservice.dto.RatesResponse
import com.alexjprog.currencyservice.service.CurrencyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rates")
class RatesController @Autowired constructor(
    private val currencyService: CurrencyService
) {
    @GetMapping
    fun getRates(
        @RequestParam("from_cur", defaultValue = "UAH") fromCurrencyName: String,
        @RequestParam("to_curs", required = false) toCurrencyNames: List<String>
    ): RatesResponse {
        return RatesResponse(
            inputCurrencyName = fromCurrencyName,
            rates = currencyService.getConversionRatesForCurrency(fromCurrencyName, toCurrencyNames)
                .associateBy { it.outputCurrencyName }
                .mapValues { RatesResponse.ConversionRate(it.value.outputCurrencyName, it.value.rate) }
        )
    }
}