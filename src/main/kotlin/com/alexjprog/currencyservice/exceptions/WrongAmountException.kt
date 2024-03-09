package com.alexjprog.currencyservice.exceptions

class WrongAmountException(amount: Double): IllegalArgumentException("Amount is not positive: $amount")