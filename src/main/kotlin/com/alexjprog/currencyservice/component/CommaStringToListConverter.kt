package com.alexjprog.currencyservice.component

import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class CommaStringToListConverter : Converter<String, List<String>> {
    override fun convert(source: String): List<String> {
        return source.split(",".toRegex()).dropLastWhile { it.isEmpty() }
    }
}