package com.alexjprog.currencyservice.repository

import com.alexjprog.currencyservice.db.entity.Rate
import com.alexjprog.currencyservice.db.entity.RateId
import org.springframework.data.repository.Repository
import org.springframework.data.util.Streamable

@org.springframework.stereotype.Repository
interface RateRepository: Repository<Rate, RateId> {
    fun findById_InputCurrency_NameAndId_OutputCurrency_NameInOrderById_OutputCurrency_NameAsc(fromName: String, toNames: Collection<String>): Streamable<Rate>
    fun findById_InputCurrency_NameOrderById_OutputCurrency_NameAsc(fromName: String): Streamable<Rate>
}