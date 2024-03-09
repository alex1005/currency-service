package com.alexjprog.currencyservice.controller

import com.alexjprog.currencyservice.dto.CurrencyResponse
import com.alexjprog.currencyservice.service.CurrencyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/currencies")
class CurrencyController @Autowired constructor(
    private val currencyService: CurrencyService
) {
    @GetMapping
    fun getCurrenciesInfo(
        @RequestParam("req_curs", required = false, defaultValue = "") requestedCurrencyNames: List<String>
    ): List<CurrencyResponse> {
        return currencyService.getAvailableCurrencies(requestedCurrencyNames)
            .map { CurrencyResponse(it.name, it.longName) }
    }
}