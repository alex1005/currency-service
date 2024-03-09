package com.alexjprog.currencyservice.service

import com.alexjprog.currencyservice.exceptions.WrongAmountException
import com.alexjprog.currencyservice.exceptions.WrongBaseCurrencyException
import com.alexjprog.currencyservice.exceptions.WrongCurrenciesException
import com.alexjprog.currencyservice.repository.CurrencyRepository
import com.alexjprog.currencyservice.repository.RateRepository
import com.alexjprog.currencyservice.service.model.ConversionResultModel
import com.alexjprog.currencyservice.service.model.CurrencyModel
import com.alexjprog.currencyservice.service.model.RateModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CurrencyServiceImpl @Autowired constructor(
    private val rateRepository: RateRepository,
    private val currencyRepository: CurrencyRepository
): CurrencyService {
    override fun getAvailableCurrencies(currencyNames: List<String>): List<CurrencyModel> {
        return if(currencyNames.isEmpty()) {
            currencyRepository.findAll().toList()
        } else {
            val result = currencyRepository.findByNameInOrderByNameAsc(currencyNames).toList()
            if(result.size != currencyNames.size) {
                val resultNames = result.map { it.name }
                val unknownCurrencies = currencyNames.filter { it !in resultNames }
                if (unknownCurrencies.isNotEmpty()) throw WrongCurrenciesException(unknownCurrencies)
            }

            result
        }.map {
            CurrencyModel(it.name, it.longName)
        }
    }

    override fun getConversionRatesForCurrency(fromName: String, toNames: List<String>): List<RateModel> {
        return loadRatesForCurrency(fromName, toNames).toList()
    }

    override fun convertToCurrencies(
        fromName: String,
        amount: Double,
        toNames: List<String>
    ): List<ConversionResultModel> {
        if(amount < 0.0) throw WrongAmountException(amount)
        return loadRatesForCurrency(fromName, toNames).map {
            ConversionResultModel(it, amount, convertCurrency(amount, it.rate))
        }.toList()
    }

    private fun loadRatesForCurrency(fromName: String, toNames: List<String>): List<RateModel> {
        return if(toNames.isEmpty()) {
            rateRepository.findById_InputCurrency_NameOrderById_OutputCurrency_NameAsc(fromName).toList()
        } else {
            val result = rateRepository.findById_InputCurrency_NameAndId_OutputCurrency_NameInOrderById_OutputCurrency_NameAsc(fromName, toNames).toList()
            if(result.isEmpty()) throw WrongBaseCurrencyException(fromName)
            if(result.size != toNames.size) {
                val resultNames = result.map { it.id.outputCurrency.name }
                val unknownCurrencies = toNames.filter { it !in resultNames }
                if (unknownCurrencies.isNotEmpty()) throw WrongCurrenciesException(unknownCurrencies)
            }

            result
        }.map {
            RateModel(it.id.inputCurrency.name, it.id.outputCurrency.name, it.price)
        }
    }

    private fun convertCurrency(amount: Double, rate: Double): Double = amount * rate
}