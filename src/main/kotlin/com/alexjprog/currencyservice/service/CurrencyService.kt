package com.alexjprog.currencyservice.service

import com.alexjprog.currencyservice.service.model.ConversionResultModel
import com.alexjprog.currencyservice.service.model.CurrencyModel
import com.alexjprog.currencyservice.service.model.RateModel

interface CurrencyService {
    fun getAvailableCurrencies(currencyNames: List<String>): List<CurrencyModel>
    fun getConversionRatesForCurrency(fromName:String, toNames: List<String>): List<RateModel>
    fun convertToCurrencies(fromName:String, amount: Double, toNames: List<String>): List<ConversionResultModel>
}