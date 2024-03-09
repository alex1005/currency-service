package com.alexjprog.currencyservice.db.entity

import jakarta.persistence.*
import java.io.Serializable


@Embeddable
open class RateId(
    @ManyToOne
    @JoinColumn(nullable = false)
    open var inputCurrency: Currency,
    @ManyToOne
    @JoinColumn(nullable = false)
    open var outputCurrency: Currency
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is RateId) return false

        if (inputCurrency != other.inputCurrency) return false
        if (outputCurrency != other.outputCurrency) return false

        return true
    }

    override fun hashCode(): Int {
        var result = inputCurrency.hashCode()
        result = 31 * result + outputCurrency.hashCode()
        return result
    }

}

@Entity
@Table(name = "rates")
open class Rate(
    @EmbeddedId
    open var id: RateId,
    open var price: Double
)