package com.alexjprog.currencyservice.exceptions

class WrongCurrenciesException(currencyList: List<String>): IllegalArgumentException("Unknown currencies: ${currencyList.joinToString()}")