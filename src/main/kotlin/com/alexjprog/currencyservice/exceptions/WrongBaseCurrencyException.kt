package com.alexjprog.currencyservice.exceptions

class WrongBaseCurrencyException(base: String): IllegalArgumentException("Unknown base currency: $base")