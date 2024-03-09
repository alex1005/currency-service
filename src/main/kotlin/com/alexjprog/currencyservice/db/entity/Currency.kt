package com.alexjprog.currencyservice.db.entity

import jakarta.persistence.*

@Entity
@Table(name = "currencies")
open class Currency(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    open var id: Long,
    @Column(nullable = false, unique = true)
    open var name: String,
    open var longName: String? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Currency) return false

        if (id != other.id) return false
        if (name != other.name) return false
        if (longName != other.longName) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + (longName?.hashCode() ?: 0)
        return result
    }
}