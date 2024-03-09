package com.alexjprog.currencyservice.repository

import com.alexjprog.currencyservice.db.entity.Currency
import org.springframework.data.repository.Repository
import org.springframework.data.util.Streamable

@org.springframework.stereotype.Repository
interface CurrencyRepository: Repository<Currency, Long> {
    fun findByNameInOrderByNameAsc(names: Collection<String>): Streamable<Currency>
    fun findAll(): Streamable<Currency>
}